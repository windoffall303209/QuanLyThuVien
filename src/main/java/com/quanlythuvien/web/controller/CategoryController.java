package com.quanlythuvien.web.controller;

import com.quanlythuvien.domain.Category;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.service.CategoryService;
import com.quanlythuvien.web.form.CategoryForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
// Điều phối các thao tác quản lý thể loại.
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    // Hiển thị danh sách dữ liệu chính của màn hình này.
    public String index(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/index";
    }

    @GetMapping("/categories/create")
    // Chuẩn bị dữ liệu cho màn hình tạo mới.
    public String createForm(Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        model.addAttribute("pageTitle", "Thêm thể loại");
        model.addAttribute("formAction", "/categories/create");
        return "categories/form";
    }

    @PostMapping("/categories/create")
    public String create(@Valid @ModelAttribute("categoryForm") CategoryForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Thêm thể loại");
            model.addAttribute("formAction", "/categories/create");
            return "categories/form";
        }
        try {
            categoryService.create(form);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm thể loại thành công");
            return "redirect:/categories";
        } catch (BusinessException ex) {
            model.addAttribute("pageTitle", "Thêm thể loại");
            model.addAttribute("formAction", "/categories/create");
            model.addAttribute("errorMessage", ex.getMessage());
            return "categories/form";
        }
    }

    @GetMapping("/categories/{id}/edit")
    // Chuẩn bị dữ liệu cho màn hình cập nhật.
    public String editForm(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        CategoryForm form = new CategoryForm();
        form.setName(category.getName());
        form.setDescription(category.getDescription());
        model.addAttribute("categoryForm", form);
        model.addAttribute("pageTitle", "Cập nhật thể loại");
        model.addAttribute("formAction", "/categories/" + id + "/edit");
        return "categories/form";
    }

    @PostMapping("/categories/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("categoryForm") CategoryForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Cập nhật thể loại");
            model.addAttribute("formAction", "/categories/" + id + "/edit");
            return "categories/form";
        }
        try {
            categoryService.update(id, form);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thể loại thành công");
            return "redirect:/categories";
        } catch (BusinessException ex) {
            model.addAttribute("pageTitle", "Cập nhật thể loại");
            model.addAttribute("formAction", "/categories/" + id + "/edit");
            model.addAttribute("errorMessage", ex.getMessage());
            return "categories/form";
        }
    }

    @PostMapping("/categories/{id}/delete")
    // Xử lý yêu cầu xóa dữ liệu liên quan.
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thể loại thành công");
        } catch (BusinessException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/categories";
    }
}
