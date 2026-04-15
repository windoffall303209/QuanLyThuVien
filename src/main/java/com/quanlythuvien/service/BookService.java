package com.quanlythuvien.service;

import com.quanlythuvien.domain.Book;
import com.quanlythuvien.web.form.BookForm;
import java.util.List;

// Khai báo các chức năng nghiệp vụ cho sách.
public interface BookService {

    List<Book> search(String keyword);

    List<Book> findAvailableBooks();

    Book findById(Long id);

    Book create(BookForm form);

    Book update(Long id, BookForm form);

    void deactivate(Long id);
}
