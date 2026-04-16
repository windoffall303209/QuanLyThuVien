package com.quanlythuvien.service.impl;

import com.quanlythuvien.config.properties.AppProperties;
import com.quanlythuvien.domain.BanSao;
import com.quanlythuvien.domain.BanSaoId;
import com.quanlythuvien.domain.Muon;
import com.quanlythuvien.domain.MuonId;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.exception.ResourceNotFoundException;
import com.quanlythuvien.repository.BanSaoRepository;
import com.quanlythuvien.repository.MuonRepository;
import com.quanlythuvien.repository.ThanhVienRepository;
import com.quanlythuvien.repository.ThuThuRepository;
import com.quanlythuvien.service.MuonService;
import com.quanlythuvien.web.form.MuonForm;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MuonServiceImpl implements MuonService {

    private final MuonRepository muonRepository;
    private final ThanhVienRepository thanhVienRepository;
    private final ThuThuRepository thuThuRepository;
    private final BanSaoRepository banSaoRepository;
    private final AppProperties appProperties;

    public MuonServiceImpl(MuonRepository muonRepository,
                           ThanhVienRepository thanhVienRepository,
                           ThuThuRepository thuThuRepository,
                           BanSaoRepository banSaoRepository,
                           AppProperties appProperties) {
        this.muonRepository = muonRepository;
        this.thanhVienRepository = thanhVienRepository;
        this.thuThuRepository = thuThuRepository;
        this.banSaoRepository = banSaoRepository;
        this.appProperties = appProperties;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Muon> findDangMuon() {
        return muonRepository.findByNgayTraIsNullOrderByNgayHenTraAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Muon> findTatCa() {
        return muonRepository.findAll().stream()
                .sorted((a, b) -> b.getNgayMuon().compareTo(a.getNgayMuon()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Muon> findQuaHan() {
        return muonRepository.findOverdue(LocalDate.now());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Muon> findGanDay() {
        return muonRepository.findTop5ByOrderByNgayMuonDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Muon> findByThanhVien(String maThanhVien) {
        return muonRepository.findByMaThanhVienOrderByNgayMuonDesc(maThanhVien);
    }

    @Override
    public MuonForm buildDefaultForm() {
        MuonForm form = new MuonForm();
        form.setNgayMuon(LocalDate.now());
        form.setNgayHenTra(LocalDate.now().plusDays(appProperties.defaultBorrowDays()));
        return form;
    }

    @Override
    public Muon create(MuonForm form) {
        if (form.getNgayHenTra().isBefore(form.getNgayMuon())) {
            throw new BusinessException("Ngày hẹn trả phải lớn hơn hoặc bằng ngày mượn");
        }
        String maThanhVien = form.getMaThanhVien().trim();
        String maThuThu = form.getMaThuThu().trim();
        String maSach = form.getMaSach().trim();

        var thanhVien = thanhVienRepository.findById(maThanhVien)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thành viên"));
        if (!"Hoạt động".equalsIgnoreCase(thanhVien.getTinhTrangThe())) {
            throw new BusinessException("Thành viên này hiện không hoạt động");
        }

        thuThuRepository.findById(maThuThu)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thủ thư"));

        BanSaoId banSaoId = new BanSaoId(maSach, form.getCopyNo());
        BanSao banSao = banSaoRepository.findById(banSaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bản sao sách"));

        if (!"Chưa mượn".equalsIgnoreCase(banSao.getTinhTrangMuon())) {
            throw new BusinessException("Bản sao này hiện không sẵn sàng để mượn");
        }

        Muon muon = new Muon();
        muon.setMaThanhVien(maThanhVien);
        muon.setMaThuThu(maThuThu);
        muon.setMaSach(maSach);
        muon.setCopyNo(form.getCopyNo());
        muon.setNgayMuon(form.getNgayMuon());
        muon.setNgayHenTra(form.getNgayHenTra());
        muon.setTrangThai("Đang mượn");
        muon.setGhiChu(form.getGhiChu() == null || form.getGhiChu().isBlank() ? null : form.getGhiChu().trim());

        banSao.setTinhTrangMuon("Đang mượn");
        banSaoRepository.save(banSao);

        return muonRepository.save(muon);
    }

    @Override
    public void traSach(MuonId id) {
        Muon muon = muonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu mượn"));
        if (muon.getNgayTra() != null) {
            throw new BusinessException("Phiếu mượn này đã trả sách");
        }

        BanSaoId banSaoId = new BanSaoId(muon.getMaSach(), muon.getCopyNo());
        BanSao banSao = banSaoRepository.findById(banSaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bản sao sách"));

        muon.setNgayTra(LocalDate.now());
        muon.setTrangThai("Đã trả");
        banSao.setTinhTrangMuon("Chưa mượn");

        muonRepository.save(muon);
        banSaoRepository.save(banSao);
    }
}
