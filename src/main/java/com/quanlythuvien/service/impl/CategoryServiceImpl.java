package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.Category;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.exception.ResourceNotFoundException;
import com.quanlythuvien.repository.BookRepository;
import com.quanlythuvien.repository.CategoryRepository;
import com.quanlythuvien.service.CategoryService;
import com.quanlythuvien.web.form.CategoryForm;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
// Triển khai nghiệp vụ quản lý thể loại.
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    // Lấy toàn bộ dữ liệu theo điều kiện mặc định.
    public List<Category> findAll() {
        return categoryRepository.findAll().stream()
                .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    // Tìm dữ liệu theo khóa chính.
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thể loại"));
    }

    @Override
    // Xử lý yêu cầu tạo mới từ biểu mẫu.
    public Category create(CategoryForm form) {
        if (categoryRepository.existsByNameIgnoreCase(form.getName().trim())) {
            throw new BusinessException("Tên thể loại đã tồn tại");
        }
        Category category = new Category();
        applyForm(category, form);
        return categoryRepository.save(category);
    }

    @Override
    // Xử lý yêu cầu cập nhật dữ liệu.
    public Category update(Long id, CategoryForm form) {
        Category category = findById(id);
        categoryRepository.findByNameIgnoreCase(form.getName().trim())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new BusinessException("Tên thể loại đã tồn tại");
                });
        applyForm(category, form);
        return categoryRepository.save(category);
    }

    @Override
    // Xử lý yêu cầu xóa dữ liệu liên quan.
    public void delete(Long id) {
        Category category = findById(id);
        boolean hasBooks = bookRepository.countByCategoryIdAndActiveTrue(category.getId()) > 0;
        if (hasBooks) {
            throw new BusinessException("Không thể xóa thể loại đang có sách sử dụng");
        }
        categoryRepository.delete(category);
    }

    // Đồng bộ dữ liệu từ biểu mẫu vào thực thể.
    private void applyForm(Category category, CategoryForm form) {
        category.setName(form.getName().trim());
        category.setDescription(form.getDescription() == null ? null : form.getDescription().trim());
    }
}
