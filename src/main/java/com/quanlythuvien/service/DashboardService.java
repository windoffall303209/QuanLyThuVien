package com.quanlythuvien.service;

import com.quanlythuvien.service.dto.DashboardStats;

// Khai báo dữ liệu cần thiết cho trang tổng quan.
public interface DashboardService {

    DashboardStats getStats();
}
