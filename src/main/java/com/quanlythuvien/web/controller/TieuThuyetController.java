package com.quanlythuvien.web.controller;

import com.quanlythuvien.service.TieuThuyetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TieuThuyetController {

    private final TieuThuyetService tieuThuyetService;

    public TieuThuyetController(TieuThuyetService tieuThuyetService) {
        this.tieuThuyetService = tieuThuyetService;
    }

    @GetMapping("/tieu-thuyet")
    public String index(Model model) {
        model.addAttribute("items", tieuThuyetService.findAll());
        return "tieu-thuyet/index";
    }
}
