package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.Book;
import com.quanlythuvien.domain.BorrowStatus;
import com.quanlythuvien.domain.Category;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.exception.ResourceNotFoundException;
import com.quanlythuvien.repository.BookRepository;
import com.quanlythuvien.repository.BorrowRecordRepository;
import com.quanlythuvien.repository.CategoryRepository;
import com.quanlythuvien.service.BookService;
import com.quanlythuvien.web.form.BookForm;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
// Triển khai nghiệp vụ quản lý sách và tồn kho.
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           CategoryRepository categoryRepository,
                           BorrowRecordRepository borrowRecordRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    @Override
    @Transactional(readOnly = true)
    // Tìm kiếm dữ liệu theo từ khóa đầu vào.
    public List<Book> search(String keyword) {
        return bookRepository.search(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    // Xử lý chức năng findAvailableBooks của lớp hiện tại.
    public List<Book> findAvailableBooks() {
        return bookRepository.findAllByOrderByTitleAsc().stream()
                .filter(Book::isActive)
                .filter(book -> book.getAvailableCopies() > 0)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    // Tìm dữ liệu theo khóa chính.
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách"));
    }

    @Override
    // Xử lý yêu cầu tạo mới từ biểu mẫu.
    public Book create(BookForm form) {
        if (bookRepository.existsByIsbn(form.getIsbn().trim())) {
            throw new BusinessException("ISBN đã tồn tại");
        }
        Book book = new Book();
        applyForm(book, form);
        return bookRepository.save(book);
    }

    @Override
    // Xử lý yêu cầu cập nhật dữ liệu.
    public Book update(Long id, BookForm form) {
        Book book = findById(id);
        bookRepository.findByIsbn(form.getIsbn().trim())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new BusinessException("ISBN đã tồn tại");
                });
        applyForm(book, form);
        return bookRepository.save(book);
    }

    @Override
    // Xử lý yêu cầu ngừng sử dụng dữ liệu liên quan.
    public void deactivate(Long id) {
        Book book = findById(id);
        boolean hasActiveBorrow = borrowRecordRepository.existsByBookIdAndStatusIn(book.getId(), List.of(BorrowStatus.BORROWED, BorrowStatus.OVERDUE));
        if (hasActiveBorrow) {
            throw new BusinessException("Không thể ngừng sử dụng sách đang có lượt mượn hoạt động");
        }
        book.setActive(false);
        bookRepository.save(book);
    }

    // Đồng bộ dữ liệu từ biểu mẫu vào thực thể.
    private void applyForm(Book book, BookForm form) {
        if (form.getAvailableCopies() > form.getTotalCopies()) {
            throw new BusinessException("Số lượng còn không được lớn hơn tổng số lượng");
        }
        Category category = categoryRepository.findById(form.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thể loại"));
        book.setIsbn(form.getIsbn().trim());
        book.setTitle(form.getTitle().trim());
        book.setAuthor(form.getAuthor().trim());
        book.setPublisher(form.getPublisher() == null ? null : form.getPublisher().trim());
        book.setPublishedYear(form.getPublishedYear());
        book.setTotalCopies(form.getTotalCopies());
        book.setAvailableCopies(form.getAvailableCopies());
        book.setCategory(category);
        book.setActive(true);
    }
}
