package com.quanlythuvien.service.dto;

// Gom nhóm dữ liệu thống kê cho trang tổng quan.
public record DashboardStats(
        long totalBooks,
        long totalAvailableCopies,
        long totalReaders,
        long activeBorrows,
        long overdueBorrows
) {
}
