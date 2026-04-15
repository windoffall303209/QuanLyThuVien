package com.quanlythuvien.web.controller;

import com.quanlythuvien.exception.ResourceNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
// Xử lý lỗi chung và trả về giao diện phù hợp.
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    // Xử lý chức năng handleNotFound của lớp hiện tại.
    public String handleNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("errorTitle", "Không tìm thấy dữ liệu");
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/general";
    }

    @ExceptionHandler(Exception.class)
    // Xử lý chức năng handleGeneral của lớp hiện tại.
    public String handleGeneral(Exception ex, Model model) {
        model.addAttribute("errorTitle", "Đã xảy ra lỗi");
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/general";
    }
}
