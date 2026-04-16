package com.quanlythuvien.web.controller;

import com.quanlythuvien.service.BanSaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BanSaoController {

    private final BanSaoService banSaoService;

    public BanSaoController(BanSaoService banSaoService) {
        this.banSaoService = banSaoService;
    }

    @GetMapping("/ban-sao")
    public String index(Model model) {
        model.addAttribute("items", banSaoService.findAll());
        return "ban-sao/index";
    }
}
