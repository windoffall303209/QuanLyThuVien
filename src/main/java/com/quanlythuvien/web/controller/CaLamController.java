package com.quanlythuvien.web.controller;

import com.quanlythuvien.service.CaLamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CaLamController {

    private final CaLamService caLamService;

    public CaLamController(CaLamService caLamService) {
        this.caLamService = caLamService;
    }

    @GetMapping("/ca-lam")
    public String index(Model model) {
        model.addAttribute("items", caLamService.findAll());
        return "ca-lam/index";
    }
}
