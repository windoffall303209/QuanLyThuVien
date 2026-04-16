package com.quanlythuvien.web.controller;

import com.quanlythuvien.service.TapChiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TapChiController {

    private final TapChiService tapChiService;

    public TapChiController(TapChiService tapChiService) {
        this.tapChiService = tapChiService;
    }

    @GetMapping("/tap-chi")
    public String index(Model model) {
        model.addAttribute("items", tapChiService.findAll());
        return "tap-chi/index";
    }
}
