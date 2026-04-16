package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.TapChi;
import com.quanlythuvien.repository.TapChiRepository;
import com.quanlythuvien.service.TapChiService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TapChiServiceImpl implements TapChiService {

    private final TapChiRepository tapChiRepository;

    public TapChiServiceImpl(TapChiRepository tapChiRepository) {
        this.tapChiRepository = tapChiRepository;
    }

    @Override
    public List<TapChi> findAll() {
        return tapChiRepository.findAll();
    }
}
