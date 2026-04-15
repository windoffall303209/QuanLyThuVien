package com.quanlythuvien.web.controller;

import com.quanlythuvien.domain.Book;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.service.BookService;
import com.quanlythuvien.service.CategoryService;
import com.quanlythuvien.web.form.BookForm;
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
// Điều phối các thao tác quản lý sách.
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;

    public BookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping("/books")
    // Hiển thị danh sách dữ liệu chính của màn hình này.
    public String index(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("books", bookService.search(keyword));
        model.addAttribute("keyword", keyword);
        return "books/index";
    }

    @GetMapping("/books/create")
    // Chuẩn bị dữ liệu cho màn hình tạo mới.
    public String createForm(Model model) {
        prepareForm(model, new BookForm(), "Thêm sách", "/books/create");
        return "books/form";
    }

    @PostMapping("/books/create")
    public String create(@Valid @ModelAttribute("bookForm") BookForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form, "Thêm sách", "/books/create");
            return "books/form";
        }
        try {
            bookService.create(form);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm sách thành công");
            return "redirect:/books";
        } catch (BusinessException ex) {
            prepareForm(model, form, "Thêm sách", "/books/create");
            model.addAttribute("errorMessage", ex.getMessage());
            return "books/form";
        }
    }

    @GetMapping("/books/{id}/edit")
    // Chuẩn bị dữ liệu cho màn hình cập nhật.
    public String editForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        BookForm form = new BookForm();
        form.setIsbn(book.getIsbn());
        form.setTitle(book.getTitle());
        form.setAuthor(book.getAuthor());
        form.setPublisher(book.getPublisher());
        form.setPublishedYear(book.getPublishedYear());
        form.setTotalCopies(book.getTotalCopies());
        form.setAvailableCopies(book.getAvailableCopies());
        form.setCategoryId(book.getCategory().getId());
        prepareForm(model, form, "Cập nhật sách", "/books/" + id + "/edit");
        return "books/form";
    }

    @PostMapping("/books/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("bookForm") BookForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form, "Cập nhật sách", "/books/" + id + "/edit");
            return "books/form";
        }
        try {
            bookService.update(id, form);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật sách thành công");
            return "redirect:/books";
        } catch (BusinessException ex) {
            prepareForm(model, form, "Cập nhật sách", "/books/" + id + "/edit");
            model.addAttribute("errorMessage", ex.getMessage());
            return "books/form";
        }
    }

    @PostMapping("/books/{id}/delete")
    // Xử lý yêu cầu ngừng sử dụng dữ liệu liên quan.
    public String deactivate(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deactivate(id);
            redirectAttributes.addFlashAttribute("successMessage", "Ngừng sử dụng sách thành công");
        } catch (BusinessException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/books";
    }

    // Nạp dữ liệu nền cho biểu mẫu đang hiển thị.
    private void prepareForm(Model model, BookForm form, String pageTitle, String formAction) {
        model.addAttribute("bookForm", form);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("formAction", formAction);
    }
}
