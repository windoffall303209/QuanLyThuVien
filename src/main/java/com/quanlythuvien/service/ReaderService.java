package com.quanlythuvien.service;

import com.quanlythuvien.domain.Reader;
import com.quanlythuvien.web.form.ReaderForm;
import java.util.List;

// Khai báo các chức năng nghiệp vụ cho độc giả.
public interface ReaderService {

    List<Reader> search(String keyword);

    List<Reader> findAllActive();

    Reader findById(Long id);

    Reader create(ReaderForm form);

    Reader update(Long id, ReaderForm form);

    void deactivate(Long id);
}
