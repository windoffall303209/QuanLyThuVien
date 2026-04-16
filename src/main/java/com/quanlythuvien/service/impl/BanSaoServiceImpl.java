package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.BanSao;
import com.quanlythuvien.repository.BanSaoRepository;
import com.quanlythuvien.service.BanSaoService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BanSaoServiceImpl implements BanSaoService {

    private final BanSaoRepository banSaoRepository;

    public BanSaoServiceImpl(BanSaoRepository banSaoRepository) {
        this.banSaoRepository = banSaoRepository;
    }

    @Override
    public List<BanSao> findAll() {
        return banSaoRepository.findAll();
    }

    @Override
    public List<BanSao> findSanSangMuon() {
        return banSaoRepository.findByTinhTrangMuonOrderByNgayNhapDesc("Chưa mượn");
    }
}
