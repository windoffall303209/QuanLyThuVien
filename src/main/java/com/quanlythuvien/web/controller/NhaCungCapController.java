package com.quanlythuvien.web.controller;

import com.quanlythuvien.service.NhaCungCapService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NhaCungCapController {

    private final NhaCungCapService nhaCungCapService;

    public NhaCungCapController(NhaCungCapService nhaCungCapService) {
        this.nhaCungCapService = nhaCungCapService;
    }

    @GetMapping("/nha-cung-cap")
    public String index(Model model) {
        model.addAttribute("items", nhaCungCapService.findAll());
        return "nha-cung-cap/index";
    }
}
