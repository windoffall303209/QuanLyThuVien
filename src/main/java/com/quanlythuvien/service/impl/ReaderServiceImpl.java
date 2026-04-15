package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.Reader;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.exception.ResourceNotFoundException;
import com.quanlythuvien.repository.ReaderRepository;
import com.quanlythuvien.service.ReaderService;
import com.quanlythuvien.web.form.ReaderForm;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
// Triển khai nghiệp vụ quản lý độc giả.
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;

    public ReaderServiceImpl(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    // Tìm kiếm dữ liệu theo từ khóa đầu vào.
    public List<Reader> search(String keyword) {
        return readerRepository.search(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    // Lấy danh sách dữ liệu đang hoạt động.
    public List<Reader> findAllActive() {
        return readerRepository.findAll().stream()
                .filter(Reader::isActive)
                .sorted((a, b) -> a.getFullName().compareToIgnoreCase(b.getFullName()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    // Tìm dữ liệu theo khóa chính.
    public Reader findById(Long id) {
        return readerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy độc giả"));
    }

    @Override
    // Xử lý yêu cầu tạo mới từ biểu mẫu.
    public Reader create(ReaderForm form) {
        if (readerRepository.existsByReaderCode(form.getReaderCode().trim())) {
            throw new BusinessException("Mã độc giả đã tồn tại");
        }
        Reader reader = new Reader();
        applyForm(reader, form);
        return readerRepository.save(reader);
    }

    @Override
    // Xử lý yêu cầu cập nhật dữ liệu.
    public Reader update(Long id, ReaderForm form) {
        Reader reader = findById(id);
        readerRepository.findByReaderCode(form.getReaderCode().trim())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new BusinessException("Mã độc giả đã tồn tại");
                });
        applyForm(reader, form);
        return readerRepository.save(reader);
    }

    @Override
    // Xử lý yêu cầu ngừng sử dụng dữ liệu liên quan.
    public void deactivate(Long id) {
        Reader reader = findById(id);
        reader.setActive(false);
        readerRepository.save(reader);
    }

    // Đồng bộ dữ liệu từ biểu mẫu vào thực thể.
    private void applyForm(Reader reader, ReaderForm form) {
        reader.setReaderCode(form.getReaderCode().trim());
        reader.setFullName(form.getFullName().trim());
        reader.setEmail(form.getEmail() == null || form.getEmail().isBlank() ? null : form.getEmail().trim());
        reader.setPhone(form.getPhone() == null || form.getPhone().isBlank() ? null : form.getPhone().trim());
        reader.setAddress(form.getAddress() == null || form.getAddress().isBlank() ? null : form.getAddress().trim());
        reader.setActive(true);
    }
}
