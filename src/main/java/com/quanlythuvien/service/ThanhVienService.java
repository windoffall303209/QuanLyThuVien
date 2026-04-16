package com.quanlythuvien.service;

import com.quanlythuvien.domain.ThanhVien;
import com.quanlythuvien.web.form.ThanhVienForm;
import java.util.List;

public interface ThanhVienService {

    List<ThanhVien> search(String keyword);

    List<ThanhVien> findAllHoatDong();

    ThanhVien findById(String maThanhVien);

    ThanhVien create(ThanhVienForm form);

    ThanhVien update(String maThanhVien, ThanhVienForm form);

    void delete(String maThanhVien);
}
