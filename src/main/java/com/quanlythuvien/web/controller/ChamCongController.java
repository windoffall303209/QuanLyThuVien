package com.quanlythuvien.web.controller;

import com.quanlythuvien.service.ChamCongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChamCongController {

    private final ChamCongService chamCongService;

    public ChamCongController(ChamCongService chamCongService) {
        this.chamCongService = chamCongService;
    }

    @GetMapping("/cham-cong")
    public String index(Model model) {
        model.addAttribute("items", chamCongService.findAll());
        return "cham-cong/index";
    }
}
