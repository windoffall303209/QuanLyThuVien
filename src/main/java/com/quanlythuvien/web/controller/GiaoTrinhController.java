package com.quanlythuvien.web.controller;

import com.quanlythuvien.service.GiaoTrinhService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GiaoTrinhController {

    private final GiaoTrinhService giaoTrinhService;

    public GiaoTrinhController(GiaoTrinhService giaoTrinhService) {
        this.giaoTrinhService = giaoTrinhService;
    }

    @GetMapping("/giao-trinh")
    public String index(Model model) {
        model.addAttribute("items", giaoTrinhService.findAll());
        return "giao-trinh/index";
    }
}
