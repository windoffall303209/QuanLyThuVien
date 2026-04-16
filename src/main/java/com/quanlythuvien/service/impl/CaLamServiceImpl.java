package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.CaLam;
import com.quanlythuvien.repository.CaLamRepository;
import com.quanlythuvien.service.CaLamService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CaLamServiceImpl implements CaLamService {

    private final CaLamRepository caLamRepository;

    public CaLamServiceImpl(CaLamRepository caLamRepository) {
        this.caLamRepository = caLamRepository;
    }

    @Override
    public List<CaLam> findAll() {
        return caLamRepository.findAllByOrderByMaCaLamAsc();
    }
}
