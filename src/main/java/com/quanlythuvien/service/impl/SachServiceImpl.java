package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.Sach;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.exception.ResourceNotFoundException;
import com.quanlythuvien.repository.BanSaoRepository;
import com.quanlythuvien.repository.SachRepository;
import com.quanlythuvien.service.SachService;
import com.quanlythuvien.web.form.SachForm;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SachServiceImpl implements SachService {

    private final SachRepository sachRepository;
    private final BanSaoRepository banSaoRepository;

    public SachServiceImpl(SachRepository sachRepository, BanSaoRepository banSaoRepository) {
        this.sachRepository = sachRepository;
        this.banSaoRepository = banSaoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sach> search(String keyword) {
        return sachRepository.search(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public Sach findById(String maSach) {
        return sachRepository.findById(maSach)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách"));
    }

    @Override
    public Sach create(SachForm form) {
        String maSach = form.getMaSach().trim();
        if (sachRepository.existsById(maSach)) {
            throw new BusinessException("Mã sách đã tồn tại");
        }
        Sach sach = new Sach();
        applyForm(sach, form);
        return sachRepository.save(sach);
    }

    @Override
    public Sach update(String maSach, SachForm form) {
        Sach sach = findById(maSach);
        applyForm(sach, form);
        sach.setMaSach(maSach);
        return sachRepository.save(sach);
    }

    @Override
    public void delete(String maSach) {
        Sach sach = findById(maSach);
        if (!banSaoRepository.findByMaSachOrderByCopyNoAsc(maSach).isEmpty()) {
            throw new BusinessException("Không thể xóa sách đang có bản sao");
        }
        sachRepository.delete(sach);
    }

    private void applyForm(Sach sach, SachForm form) {
        sach.setMaSach(form.getMaSach().trim());
        sach.setTenSach(form.getTenSach().trim());
        sach.setTacGia(form.getTacGia().trim());
        sach.setNhaXb(form.getNhaXb() == null || form.getNhaXb().isBlank() ? null : form.getNhaXb().trim());
        sach.setNamXb(form.getNamXb());
        sach.setSoTrang(form.getSoTrang());
        sach.setGiaTri(form.getGiaTri());
    }
}
