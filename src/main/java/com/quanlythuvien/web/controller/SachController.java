package com.quanlythuvien.web.controller;

import com.quanlythuvien.domain.Sach;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.service.SachService;
import com.quanlythuvien.web.form.SachForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SachController {

    private final SachService sachService;

    public SachController(SachService sachService) {
        this.sachService = sachService;
    }

    @GetMapping("/sach")
    public String index(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("saches", sachService.search(keyword));
        model.addAttribute("keyword", keyword);
        return "books/index";
    }

    @GetMapping("/sach/create")
    public String createForm(Model model) {
        prepareForm(model, new SachForm(), "Thêm sách", "/sach/create");
        return "books/form";
    }

    @PostMapping("/sach/create")
    public String create(@Valid @ModelAttribute("sachForm") SachForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form, "Thêm sách", "/sach/create");
            return "books/form";
        }
        try {
            sachService.create(form);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm sách thành công");
            return "redirect:/sach";
        } catch (BusinessException ex) {
            prepareForm(model, form, "Thêm sách", "/sach/create");
            model.addAttribute("errorMessage", ex.getMessage());
            return "books/form";
        }
    }

    @GetMapping("/sach/{maSach}/edit")
    public String editForm(@PathVariable String maSach, Model model) {
        Sach sach = sachService.findById(maSach);
        SachForm form = new SachForm();
        form.setMaSach(sach.getMaSach());
        form.setTenSach(sach.getTenSach());
        form.setTacGia(sach.getTacGia());
        form.setNhaXb(sach.getNhaXb());
        form.setNamXb(sach.getNamXb());
        form.setSoTrang(sach.getSoTrang());
        form.setGiaTri(sach.getGiaTri());
        prepareForm(model, form, "Cập nhật sách", "/sach/" + maSach + "/edit");
        model.addAttribute("disableMaSach", true);
        return "books/form";
    }

    @PostMapping("/sach/{maSach}/edit")
    public String update(@PathVariable String maSach,
                         @Valid @ModelAttribute("sachForm") SachForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form, "Cập nhật sách", "/sach/" + maSach + "/edit");
            model.addAttribute("disableMaSach", true);
            return "books/form";
        }
        try {
            sachService.update(maSach, form);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật sách thành công");
            return "redirect:/sach";
        } catch (BusinessException ex) {
            prepareForm(model, form, "Cập nhật sách", "/sach/" + maSach + "/edit");
            model.addAttribute("disableMaSach", true);
            model.addAttribute("errorMessage", ex.getMessage());
            return "books/form";
        }
    }

    @PostMapping("/sach/{maSach}/delete")
    public String delete(@PathVariable String maSach, RedirectAttributes redirectAttributes) {
        try {
            sachService.delete(maSach);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa sách thành công");
        } catch (BusinessException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/sach";
    }

    private void prepareForm(Model model, SachForm form, String pageTitle, String formAction) {
        model.addAttribute("sachForm", form);
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("formAction", formAction);
    }
}
