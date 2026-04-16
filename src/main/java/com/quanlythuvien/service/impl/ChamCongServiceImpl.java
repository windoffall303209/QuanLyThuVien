package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.ChamCong;
import com.quanlythuvien.repository.ChamCongRepository;
import com.quanlythuvien.service.ChamCongService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ChamCongServiceImpl implements ChamCongService {

    private final ChamCongRepository chamCongRepository;

    public ChamCongServiceImpl(ChamCongRepository chamCongRepository) {
        this.chamCongRepository = chamCongRepository;
    }

    @Override
    public List<ChamCong> findAll() {
        return chamCongRepository.findAll();
    }
}
