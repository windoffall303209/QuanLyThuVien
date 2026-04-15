package com.quanlythuvien.web.controller;

import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.service.BookService;
import com.quanlythuvien.service.BorrowService;
import com.quanlythuvien.service.CategoryService;
import com.quanlythuvien.service.ReaderService;
import com.quanlythuvien.web.form.BorrowForm;
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
// Điều phối các thao tác mượn trả sách.
public class BorrowController {

    private final BorrowService borrowService;
    private final ReaderService readerService;
    private final BookService bookService;
    private final CategoryService categoryService;

    public BorrowController(BorrowService borrowService,
                            ReaderService readerService,
                            BookService bookService,
                            CategoryService categoryService) {
        this.borrowService = borrowService;
        this.readerService = readerService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping("/borrows")
    // Hiển thị danh sách dữ liệu chính của màn hình này.
    public String index(Model model) {
        borrowService.refreshOverdueStatus();
        model.addAttribute("activeBorrows", borrowService.findActiveBorrows());
        model.addAttribute("allBorrows", borrowService.findAll());
        return "borrows/index";
    }

    @GetMapping("/borrows/create")
    // Chuẩn bị dữ liệu cho màn hình tạo mới.
    public String createForm(Model model) {
        prepareForm(model, borrowService.buildDefaultForm());
        return "borrows/form";
    }

    @PostMapping("/borrows/create")
    public String create(@Valid @ModelAttribute("borrowForm") BorrowForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form);
            return "borrows/form";
        }
        try {
            borrowService.create(form);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo phiếu mượn thành công");
            return "redirect:/borrows";
        } catch (BusinessException ex) {
            prepareForm(model, form);
            model.addAttribute("errorMessage", ex.getMessage());
            return "borrows/form";
        }
    }

    @PostMapping("/borrows/{id}/return")
    // Xử lý thao tác trả sách cho phiếu mượn.
    public String returnBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            borrowService.returnBook(id);
            redirectAttributes.addFlashAttribute("successMessage", "Trả sách thành công");
        } catch (BusinessException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/borrows";
    }

    @GetMapping("/borrows/overdue")
    // Hiển thị danh sách phiếu mượn quá hạn.
    public String overdue(Model model) {
        model.addAttribute("overdueBorrows", borrowService.findOverdueBorrows());
        return "borrows/overdue";
    }

    // Nạp dữ liệu nền cho biểu mẫu đang hiển thị.
    private void prepareForm(Model model, BorrowForm form) {
        model.addAttribute("borrowForm", form);
        model.addAttribute("readers", readerService.findAllActive());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("books", bookService.findAvailableBooks());
        model.addAttribute("pageTitle", "Tạo phiếu mượn");
    }
}
