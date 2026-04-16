package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.ThanhVien;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.exception.ResourceNotFoundException;
import com.quanlythuvien.repository.MuonRepository;
import com.quanlythuvien.repository.ThanhVienRepository;
import com.quanlythuvien.service.ThanhVienService;
import com.quanlythuvien.web.form.ThanhVienForm;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ThanhVienServiceImpl implements ThanhVienService {

    private final ThanhVienRepository thanhVienRepository;
    private final MuonRepository muonRepository;

    public ThanhVienServiceImpl(ThanhVienRepository thanhVienRepository, MuonRepository muonRepository) {
        this.thanhVienRepository = thanhVienRepository;
        this.muonRepository = muonRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ThanhVien> search(String keyword) {
        return thanhVienRepository.search(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ThanhVien> findAllHoatDong() {
        return thanhVienRepository.findByTinhTrangTheIgnoreCaseOrderByHoTenAsc("Hoạt động");
    }

    @Override
    @Transactional(readOnly = true)
    public ThanhVien findById(String maThanhVien) {
        return thanhVienRepository.findById(maThanhVien)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thành viên"));
    }

    @Override
    public ThanhVien create(ThanhVienForm form) {
        String maThanhVien = form.getMaThanhVien().trim();
        if (thanhVienRepository.existsById(maThanhVien)) {
            throw new BusinessException("Mã thành viên đã tồn tại");
        }
        ThanhVien thanhVien = new ThanhVien();
        applyForm(thanhVien, form);
        return thanhVienRepository.save(thanhVien);
    }

    @Override
    public ThanhVien update(String maThanhVien, ThanhVienForm form) {
        ThanhVien thanhVien = findById(maThanhVien);
        applyForm(thanhVien, form);
        thanhVien.setMaThanhVien(maThanhVien);
        return thanhVienRepository.save(thanhVien);
    }

    @Override
    public void delete(String maThanhVien) {
        ThanhVien thanhVien = findById(maThanhVien);
        boolean hasBorrowHistory = !muonRepository.findByMaThanhVienOrderByNgayMuonDesc(maThanhVien).isEmpty();
        if (hasBorrowHistory) {
            throw new BusinessException("Không thể xóa thành viên đã có lịch sử mượn");
        }
        thanhVienRepository.delete(thanhVien);
    }

    private void applyForm(ThanhVien thanhVien, ThanhVienForm form) {
        thanhVien.setMaThanhVien(form.getMaThanhVien().trim());
        thanhVien.setHoTen(form.getHoTen().trim());
        thanhVien.setSdt(form.getSdt() == null || form.getSdt().isBlank() ? null : form.getSdt().trim());
        thanhVien.setDiaChi(form.getDiaChi() == null || form.getDiaChi().isBlank() ? null : form.getDiaChi().trim());
        thanhVien.setNgayDangKy(form.getNgayDangKy());
        thanhVien.setTinhTrangThe(form.getTinhTrangThe().trim());
        thanhVien.setGhiChu(form.getGhiChu() == null || form.getGhiChu().isBlank() ? null : form.getGhiChu().trim());
    }
}
