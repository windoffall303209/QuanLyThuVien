package com.quanlythuvien.web.controller;

import com.quanlythuvien.domain.ThanhVien;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.service.MuonService;
import com.quanlythuvien.service.ThanhVienService;
import com.quanlythuvien.web.form.ThanhVienForm;
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
public class ThanhVienController {

    private final ThanhVienService thanhVienService;
    private final MuonService muonService;

    public ThanhVienController(ThanhVienService thanhVienService, MuonService muonService) {
        this.thanhVienService = thanhVienService;
        this.muonService = muonService;
    }

    @GetMapping("/thanh-vien")
    public String index(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("members", thanhVienService.search(keyword));
        model.addAttribute("keyword", keyword);
        return "readers/index";
    }

    @GetMapping("/thanh-vien/{maThanhVien}")
    public String detail(@PathVariable String maThanhVien, Model model) {
        ThanhVien thanhVien = thanhVienService.findById(maThanhVien);
        model.addAttribute("member", thanhVien);
        model.addAttribute("borrowHistories", muonService.findByThanhVien(maThanhVien));
        return "readers/detail";
    }

    @GetMapping("/thanh-vien/create")
    public String createForm(Model model) {
        ThanhVienForm form = new ThanhVienForm();
        form.setTinhTrangThe("Hoạt động");
        form.setNgayDangKy(java.time.LocalDate.now());
        prepareForm(model, form, "Thêm thành viên", "/thanh-vien/create");
        return "readers/form";
    }

    @PostMapping("/thanh-vien/create")
    public String create(@Valid @ModelAttribute("memberForm") ThanhVienForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form, "Thêm thành viên", "/thanh-vien/create");
            return "readers/form";
        }
        try {
            thanhVienService.create(form);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm thành viên thành công");
            return "redirect:/thanh-vien";
        } catch (BusinessException ex) {
            prepareForm(model, form, "Thêm thành viên", "/thanh-vien/create");
            model.addAttribute("errorMessage", ex.getMessage());
            return "readers/form";
        }
    }

    @GetMapping("/thanh-vien/{maThanhVien}/edit")
    public String editForm(@PathVariable String maThanhVien, Model model) {
        ThanhVien thanhVien = thanhVienService.findById(maThanhVien);
        ThanhVienForm form = new ThanhVienForm();
        form.setMaThanhVien(thanhVien.getMaThanhVien());
        form.setHoTen(thanhVien.getHoTen());
        form.setSdt(thanhVien.getSdt());
        form.setDiaChi(thanhVien.getDiaChi());
        form.setNgayDangKy(thanhVien.getNgayDangKy());
        form.setTinhTrangThe(thanhVien.getTinhTrangThe());
        form.setGhiChu(thanhVien.getGhiChu());
        prepareForm(model, form, "Cập nhật thành viên", "/thanh-vien/" + maThanhVien + "/edit");
        model.addAttribute("disableMaThanhVien", true);
        return "readers/form";
    }

    @PostMapping("/thanh-vien/{maThanhVien}/edit")
    public String update(@PathVariable String maThanhVien,
                         @Valid @ModelAttribute("memberForm") ThanhVienForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form, "Cập nhật thành viên", "/thanh-vien/" + maThanhVien + "/edit");
            model.addAttribute("disableMaThanhVien", true);
            return "readers/form";
        }
        try {
            thanhVienService.update(maThanhVien, form);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thành viên thành công");
            return "redirect:/thanh-vien";
        } catch (BusinessException ex) {
            prepareForm(model, form, "Cập nhật thành viên", "/thanh-vien/" + maThanhVien + "/edit");
            model.addAttribute("disableMaThanhVien", true);
            model.addAttribute("errorMessage", ex.getMessage());
            return "readers/form";
        }
    }

    @PostMapping("/thanh-vien/{maThanhVien}/delete")
    public String delete(@PathVariable String maThanhVien, RedirectAttributes redirectAttributes) {
        try {
            thanhVienService.delete(maThanhVien);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành viên thành công");
        } catch (BusinessException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/thanh-vien";
    }

    private void prepareForm(Model model, ThanhVienForm form, String pageTitle, String formAction) {
        model.addAttribute("memberForm", form);
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("formAction", formAction);
    }
}
