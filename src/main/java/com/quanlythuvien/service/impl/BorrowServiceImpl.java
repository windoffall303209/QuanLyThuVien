package com.quanlythuvien.service.impl;

import com.quanlythuvien.config.properties.AppProperties;
import com.quanlythuvien.domain.Book;
import com.quanlythuvien.domain.BorrowRecord;
import com.quanlythuvien.domain.BorrowStatus;
import com.quanlythuvien.domain.Reader;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.exception.ResourceNotFoundException;
import com.quanlythuvien.repository.BookRepository;
import com.quanlythuvien.repository.BorrowRecordRepository;
import com.quanlythuvien.repository.ReaderRepository;
import com.quanlythuvien.service.BorrowService;
import com.quanlythuvien.web.form.BorrowForm;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
// Triển khai nghiệp vụ tạo phiếu mượn và trả sách.
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;
    private final AppProperties appProperties;

    public BorrowServiceImpl(BorrowRecordRepository borrowRecordRepository,
                             ReaderRepository readerRepository,
                             BookRepository bookRepository,
                             AppProperties appProperties) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
        this.appProperties = appProperties;
    }

    @Override
    // Khởi tạo giá trị mặc định cho biểu mẫu mượn sách.
    public BorrowForm buildDefaultForm() {
        BorrowForm form = new BorrowForm();
        form.setBorrowDate(LocalDate.now());
        form.setDueDate(LocalDate.now().plusDays(appProperties.defaultBorrowDays()));
        return form;
    }

    @Override
    @Transactional(readOnly = true)
    // Lấy toàn bộ dữ liệu theo điều kiện mặc định.
    public List<BorrowRecord> findAll() {
        refreshOverdueStatus();
        return borrowRecordRepository.findAllByLatestActivityDesc();
    }

    @Override
    @Transactional(readOnly = true)
    // Lấy danh sách phiếu đang mượn hoặc quá hạn.
    public List<BorrowRecord> findActiveBorrows() {
        refreshOverdueStatus();
        return borrowRecordRepository.findByStatusInOrderByDueDateAsc(List.of(BorrowStatus.BORROWED, BorrowStatus.OVERDUE));
    }

    @Override
    @Transactional(readOnly = true)
    // Lấy danh sách phiếu mượn đang quá hạn.
    public List<BorrowRecord> findOverdueBorrows() {
        refreshOverdueStatus();
        return borrowRecordRepository.findOverdue(LocalDate.now());
    }

    @Override
    @Transactional(readOnly = true)
    // Lấy các phiếu mượn mới nhất cho màn hình tổng quan.
    public List<BorrowRecord> findRecentBorrows() {
        refreshOverdueStatus();
        return borrowRecordRepository.findRecent(PageRequest.of(0, 5));
    }

    @Override
    @Transactional(readOnly = true)
    // Lấy các phiếu đang mượn của một độc giả.
    public List<BorrowRecord> findActiveBorrowsByReader(Long readerId) {
        refreshOverdueStatus();
        return borrowRecordRepository.findByReaderIdAndStatusInOrderByDueDateAsc(readerId,
                List.of(BorrowStatus.BORROWED, BorrowStatus.OVERDUE));
    }

    @Override
    @Transactional(readOnly = true)
    // Lấy lịch sử các phiếu đã trả của một độc giả.
    public List<BorrowRecord> findReturnedBorrowsByReader(Long readerId) {
        refreshOverdueStatus();
        return borrowRecordRepository.findReturnedByReaderIdOrderByReturnDateDesc(readerId);
    }

    @Override
    @Transactional(readOnly = true)
    // Tìm dữ liệu theo khóa chính.
    public BorrowRecord findById(Long id) {
        return borrowRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu mượn"));
    }

    @Override
    // Xử lý yêu cầu tạo mới từ biểu mẫu.
    public BorrowRecord create(BorrowForm form) {
        if (form.getDueDate().isBefore(form.getBorrowDate())) {
            throw new BusinessException("Hạn trả phải lớn hơn hoặc bằng ngày mượn");
        }
        Reader reader = readerRepository.findById(form.getReaderId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy độc giả"));
        if (!reader.isActive()) {
            throw new BusinessException("Độc giả này đang bị ngừng hoạt động");
        }
        Book book = bookRepository.findById(form.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách"));
        if (book.getCategory() == null || !Objects.equals(book.getCategory().getId(), form.getCategoryId())) {
            throw new BusinessException("Sách không thuộc thể loại đã chọn");
        }
        if (!book.isActive()) {
            throw new BusinessException("Sách này đang ngừng sử dụng");
        }
        if (book.getAvailableCopies() <= 0) {
            throw new BusinessException("Sách đã hết lượt mượn");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BorrowRecord record = new BorrowRecord();
        record.setReader(reader);
        record.setBook(book);
        record.setBorrowDate(form.getBorrowDate());
        record.setDueDate(form.getDueDate());
        record.setNotes(form.getNotes() == null || form.getNotes().isBlank() ? null : form.getNotes().trim());
        record.setStatus(form.getDueDate().isBefore(LocalDate.now()) ? BorrowStatus.OVERDUE : BorrowStatus.BORROWED);
        return borrowRecordRepository.save(record);
    }

    @Override
    // Xử lý thao tác trả sách cho phiếu mượn.
    public void returnBook(Long id) {
        BorrowRecord record = findById(id);
        if (record.getStatus() == BorrowStatus.RETURNED) {
            throw new BusinessException("Phiếu mượn này đã được trả");
        }

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        record.setReturnDate(LocalDate.now());
        record.setStatus(BorrowStatus.RETURNED);
        borrowRecordRepository.save(record);
    }

    @Override
    // Cập nhật trạng thái quá hạn trước khi hiển thị dữ liệu.
    public void refreshOverdueStatus() {
        borrowRecordRepository.markOverdue(LocalDate.now());
    }
}
