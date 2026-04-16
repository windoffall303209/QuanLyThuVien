package com.quanlythuvien.web.controller;

import com.quanlythuvien.service.ThuThuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThuThuController {

    private final ThuThuService thuThuService;

    public ThuThuController(ThuThuService thuThuService) {
        this.thuThuService = thuThuService;
    }

    @GetMapping("/thu-thu")
    public String index(Model model) {
        model.addAttribute("items", thuThuService.findAll());
        return "thu-thu/index";
    }
}
