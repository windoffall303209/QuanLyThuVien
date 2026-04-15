package com.quanlythuvien.web.controller;

import com.quanlythuvien.service.BorrowService;
import com.quanlythuvien.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// Điều phối trang tổng quan của hệ thống.
public class DashboardController {

    private final DashboardService dashboardService;
    private final BorrowService borrowService;

    public DashboardController(DashboardService dashboardService, BorrowService borrowService) {
        this.dashboardService = dashboardService;
        this.borrowService = borrowService;
    }

    @GetMapping("/dashboard")
    // Xử lý chức năng dashboard của lớp hiện tại.
    public String dashboard(Model model) {
        borrowService.refreshOverdueStatus();
        model.addAttribute("stats", dashboardService.getStats());
        model.addAttribute("recentBorrows", borrowService.findRecentBorrows());
        model.addAttribute("overdueBorrows", borrowService.findOverdueBorrows());
        return "dashboard/index";
    }
}
