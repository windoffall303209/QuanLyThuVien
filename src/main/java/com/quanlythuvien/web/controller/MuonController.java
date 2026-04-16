package com.quanlythuvien.web.controller;

import com.quanlythuvien.domain.Muon;
import com.quanlythuvien.domain.MuonId;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.service.BanSaoService;
import com.quanlythuvien.service.MuonService;
import com.quanlythuvien.service.SachService;
import com.quanlythuvien.service.ThanhVienService;
import com.quanlythuvien.service.ThuThuService;
import com.quanlythuvien.web.form.MuonForm;
import jakarta.validation.Valid;
import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MuonController {

    private final MuonService muonService;
    private final ThanhVienService thanhVienService;
    private final ThuThuService thuThuService;
    private final SachService sachService;
    private final BanSaoService banSaoService;

    public MuonController(MuonService muonService,
                          ThanhVienService thanhVienService,
                          ThuThuService thuThuService,
                          SachService sachService,
                          BanSaoService banSaoService) {
        this.muonService = muonService;
        this.thanhVienService = thanhVienService;
        this.thuThuService = thuThuService;
        this.sachService = sachService;
        this.banSaoService = banSaoService;
    }

    @GetMapping("/muon-tra")
    public String index(Model model) {
        model.addAttribute("dangMuon", muonService.findDangMuon());
        model.addAttribute("tatCa", muonService.findTatCa());
        return "borrows/index";
    }

    @GetMapping("/muon-tra/create")
    public String createForm(Model model) {
        prepareForm(model, muonService.buildDefaultForm());
        return "borrows/form";
    }

    @PostMapping("/muon-tra/create")
    public String create(@Valid @ModelAttribute("muonForm") MuonForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form);
            return "borrows/form";
        }
        try {
            muonService.create(form);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo phiếu mượn thành công");
            return "redirect:/muon-tra";
        } catch (BusinessException ex) {
            prepareForm(model, form);
            model.addAttribute("errorMessage", ex.getMessage());
            return "borrows/form";
        }
    }

    @PostMapping("/muon-tra/{maThanhVien}/{maSach}/{copyNo}/{ngayMuon}/tra")
    public String traSach(@PathVariable String maThanhVien,
                          @PathVariable String maSach,
                          @PathVariable Integer copyNo,
                          @PathVariable String ngayMuon,
                          RedirectAttributes redirectAttributes) {
        try {
            MuonId id = new MuonId(maThanhVien, maSach, copyNo, LocalDate.parse(ngayMuon));
            muonService.traSach(id);
            redirectAttributes.addFlashAttribute("successMessage", "Trả sách thành công");
        } catch (BusinessException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/muon-tra";
    }

    @GetMapping("/muon-tra/qua-han")
    public String overdue(Model model) {
        model.addAttribute("overdues", muonService.findQuaHan());
        return "borrows/overdue";
    }

    private void prepareForm(Model model, MuonForm form) {
        model.addAttribute("muonForm", form);
        model.addAttribute("members", thanhVienService.findAllHoatDong());
        model.addAttribute("librarians", thuThuService.findAll());
        model.addAttribute("saches", sachService.search(null));
        model.addAttribute("availableCopies", banSaoService.findSanSangMuon());
        model.addAttribute("pageTitle", "Tạo phiếu mượn");
    }
}
