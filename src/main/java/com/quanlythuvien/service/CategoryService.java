package com.quanlythuvien.service;

import com.quanlythuvien.domain.Category;
import com.quanlythuvien.web.form.CategoryForm;
import java.util.List;

// Khai báo các chức năng nghiệp vụ cho thể loại.
public interface CategoryService {

    List<Category> findAll();

    Category findById(Long id);

    Category create(CategoryForm form);

    Category update(Long id, CategoryForm form);

    void delete(Long id);
}
