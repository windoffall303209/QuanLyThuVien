package com.quanlythuvien.web.controller;

import com.quanlythuvien.service.CungCapService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CungCapController {

    private final CungCapService cungCapService;

    public CungCapController(CungCapService cungCapService) {
        this.cungCapService = cungCapService;
    }

    @GetMapping("/cung-cap")
    public String index(Model model) {
        model.addAttribute("items", cungCapService.findAll());
        return "cung-cap/index";
    }
}
