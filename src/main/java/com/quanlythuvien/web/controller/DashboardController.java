package com.quanlythuvien.web.controller;

import com.quanlythuvien.service.DashboardService;
import com.quanlythuvien.service.MuonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// Điều phối trang tổng quan của hệ thống.
public class DashboardController {

    private final DashboardService dashboardService;
    private final MuonService muonService;

    public DashboardController(DashboardService dashboardService, MuonService muonService) {
        this.dashboardService = dashboardService;
        this.muonService = muonService;
    }

    @GetMapping("/dashboard")
    // Xử lý chức năng dashboard của lớp hiện tại.
    public String dashboard(Model model) {
        model.addAttribute("stats", dashboardService.getStats());
        model.addAttribute("recentBorrows", muonService.findGanDay());
        model.addAttribute("overdueBorrows", muonService.findQuaHan());
        return "dashboard/index";
    }
}
