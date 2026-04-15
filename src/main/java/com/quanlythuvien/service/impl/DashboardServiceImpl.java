package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.BorrowStatus;
import com.quanlythuvien.repository.BookRepository;
import com.quanlythuvien.repository.BorrowRecordRepository;
import com.quanlythuvien.repository.ReaderRepository;
import com.quanlythuvien.service.DashboardService;
import com.quanlythuvien.service.dto.DashboardStats;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
// Tổng hợp số liệu hiển thị trên trang tổng quan.
public class DashboardServiceImpl implements DashboardService {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public DashboardServiceImpl(BookRepository bookRepository,
                                ReaderRepository readerRepository,
                                BorrowRecordRepository borrowRecordRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    @Override
    public DashboardStats getStats() {
        return new DashboardStats(
                bookRepository.countByActiveTrue(),
                bookRepository.sumAvailableCopies(),
                readerRepository.countByActiveTrue(),
                borrowRecordRepository.countByStatus(BorrowStatus.BORROWED),
                borrowRecordRepository.countByStatus(BorrowStatus.OVERDUE)
        );
    }
}
