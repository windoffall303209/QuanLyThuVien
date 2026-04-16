package com.quanlythuvien.service.dto;

// Gom nhóm dữ liệu thống kê cho trang tổng quan.
public record DashboardStats(
        long totalSach,
        long totalBanSaoSanSang,
        long totalThanhVienHoatDong,
        long totalDangMuon,
        long totalQuaHan
) {
}
