package com.quanlythuvien.service;

import com.quanlythuvien.domain.Sach;
import com.quanlythuvien.web.form.SachForm;
import java.util.List;

public interface SachService {

    List<Sach> search(String keyword);

    Sach findById(String maSach);

    Sach create(SachForm form);

    Sach update(String maSach, SachForm form);

    void delete(String maSach);
}
