package com.quanlythuvien.service;

import com.quanlythuvien.domain.BorrowRecord;
import com.quanlythuvien.web.form.BorrowForm;
import java.util.List;

// Khai báo các chức năng nghiệp vụ cho mượn trả.
public interface BorrowService {

    List<BorrowRecord> findAll();

    List<BorrowRecord> findActiveBorrows();

    List<BorrowRecord> findOverdueBorrows();

    List<BorrowRecord> findRecentBorrows();

    List<BorrowRecord> findActiveBorrowsByReader(Long readerId);

    List<BorrowRecord> findReturnedBorrowsByReader(Long readerId);

    BorrowRecord findById(Long id);

    BorrowForm buildDefaultForm();

    BorrowRecord create(BorrowForm form);

    void returnBook(Long id);

    void refreshOverdueStatus();
}
