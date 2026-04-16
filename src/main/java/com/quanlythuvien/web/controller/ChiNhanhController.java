package com.quanlythuvien.web.controller;

import com.quanlythuvien.service.ChiNhanhService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChiNhanhController {

    private final ChiNhanhService chiNhanhService;

    public ChiNhanhController(ChiNhanhService chiNhanhService) {
        this.chiNhanhService = chiNhanhService;
    }

    @GetMapping("/chi-nhanh")
    public String index(Model model) {
        model.addAttribute("items", chiNhanhService.findAll());
        return "chi-nhanh/index";
    }
}
