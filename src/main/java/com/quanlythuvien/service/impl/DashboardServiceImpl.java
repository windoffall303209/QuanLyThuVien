package com.quanlythuvien.service.impl;

import com.quanlythuvien.repository.BanSaoRepository;
import com.quanlythuvien.repository.MuonRepository;
import com.quanlythuvien.repository.SachRepository;
import com.quanlythuvien.repository.ThanhVienRepository;
import com.quanlythuvien.service.DashboardService;
import com.quanlythuvien.service.dto.DashboardStats;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
// Tổng hợp số liệu hiển thị trên trang tổng quan.
public class DashboardServiceImpl implements DashboardService {

    private final SachRepository sachRepository;
    private final BanSaoRepository banSaoRepository;
    private final ThanhVienRepository thanhVienRepository;
    private final MuonRepository muonRepository;

    public DashboardServiceImpl(SachRepository sachRepository,
                                BanSaoRepository banSaoRepository,
                                ThanhVienRepository thanhVienRepository,
                                MuonRepository muonRepository) {
        this.sachRepository = sachRepository;
        this.banSaoRepository = banSaoRepository;
        this.thanhVienRepository = thanhVienRepository;
        this.muonRepository = muonRepository;
    }

    @Override
    public DashboardStats getStats() {
        long dangMuon = muonRepository.findByNgayTraIsNullOrderByNgayHenTraAsc().size();
        long quaHan = muonRepository.findOverdue(java.time.LocalDate.now()).size();
        return new DashboardStats(
                sachRepository.count(),
                banSaoRepository.countByTinhTrangMuonIgnoreCase("Chưa mượn"),
                thanhVienRepository.findByTinhTrangTheIgnoreCaseOrderByHoTenAsc("Hoạt động").size(),
                dangMuon,
                quaHan
        );
    }
}
