package com.quanlythuvien.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// Điều phối luồng đăng nhập và chuyển hướng ban đầu.
public class AuthController {

    @GetMapping("/login")
    // Xử lý chức năng login của lớp hiện tại.
    public String login() {
        return "auth/login";
    }

    @GetMapping("/")
    // Xử lý chức năng home của lớp hiện tại.
    public String home() {
        return "redirect:/dashboard";
    }
}
