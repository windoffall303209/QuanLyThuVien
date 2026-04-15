package com.quanlythuvien.web.controller;

import com.quanlythuvien.domain.Reader;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.service.BorrowService;
import com.quanlythuvien.service.ReaderService;
import com.quanlythuvien.web.form.ReaderForm;
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
// Điều phối các thao tác quản lý độc giả.
public class ReaderController {

    private final ReaderService readerService;
    private final BorrowService borrowService;

    public ReaderController(ReaderService readerService, BorrowService borrowService) {
        this.readerService = readerService;
        this.borrowService = borrowService;
    }

    @GetMapping("/readers")
    // Hiển thị danh sách dữ liệu chính của màn hình này.
    public String index(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("readers", readerService.search(keyword));
        model.addAttribute("keyword", keyword);
        return "readers/index";
    }

    @GetMapping("/readers/{id}")
    // Hiển thị chi tiết dữ liệu được chọn.
    public String detail(@PathVariable Long id, Model model) {
        Reader reader = readerService.findById(id);
        model.addAttribute("reader", reader);
        model.addAttribute("activeBorrows", borrowService.findActiveBorrowsByReader(id));
        model.addAttribute("returnedBorrows", borrowService.findReturnedBorrowsByReader(id));
        return "readers/detail";
    }

    @GetMapping("/readers/create")
    // Chuẩn bị dữ liệu cho màn hình tạo mới.
    public String createForm(Model model) {
        prepareForm(model, new ReaderForm(), "Thêm độc giả", "/readers/create");
        return "readers/form";
    }

    @PostMapping("/readers/create")
    public String create(@Valid @ModelAttribute("readerForm") ReaderForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form, "Thêm độc giả", "/readers/create");
            return "readers/form";
        }
        try {
            readerService.create(form);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm độc giả thành công");
            return "redirect:/readers";
        } catch (BusinessException ex) {
            prepareForm(model, form, "Thêm độc giả", "/readers/create");
            model.addAttribute("errorMessage", ex.getMessage());
            return "readers/form";
        }
    }

    @GetMapping("/readers/{id}/edit")
    // Chuẩn bị dữ liệu cho màn hình cập nhật.
    public String editForm(@PathVariable Long id, Model model) {
        Reader reader = readerService.findById(id);
        ReaderForm form = new ReaderForm();
        form.setReaderCode(reader.getReaderCode());
        form.setFullName(reader.getFullName());
        form.setEmail(reader.getEmail());
        form.setPhone(reader.getPhone());
        form.setAddress(reader.getAddress());
        prepareForm(model, form, "Cập nhật độc giả", "/readers/" + id + "/edit");
        return "readers/form";
    }

    @PostMapping("/readers/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("readerForm") ReaderForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form, "Cập nhật độc giả", "/readers/" + id + "/edit");
            return "readers/form";
        }
        try {
            readerService.update(id, form);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật độc giả thành công");
            return "redirect:/readers";
        } catch (BusinessException ex) {
            prepareForm(model, form, "Cập nhật độc giả", "/readers/" + id + "/edit");
            model.addAttribute("errorMessage", ex.getMessage());
            return "readers/form";
        }
    }

    @PostMapping("/readers/{id}/delete")
    // Xử lý yêu cầu ngừng sử dụng dữ liệu liên quan.
    public String deactivate(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        readerService.deactivate(id);
        redirectAttributes.addFlashAttribute("successMessage", "Ngừng hoạt động độc giả thành công");
        return "redirect:/readers";
    }

    // Nạp dữ liệu nền cho biểu mẫu đang hiển thị.
    private void prepareForm(Model model, ReaderForm form, String pageTitle, String formAction) {
        model.addAttribute("readerForm", form);
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("formAction", formAction);
    }
}
