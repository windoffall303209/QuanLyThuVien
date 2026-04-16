package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.ChiNhanh;
import com.quanlythuvien.repository.ChiNhanhRepository;
import com.quanlythuvien.service.ChiNhanhService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ChiNhanhServiceImpl implements ChiNhanhService {

    private final ChiNhanhRepository chiNhanhRepository;

    public ChiNhanhServiceImpl(ChiNhanhRepository chiNhanhRepository) {
        this.chiNhanhRepository = chiNhanhRepository;
    }

    @Override
    public List<ChiNhanh> findAll() {
        return chiNhanhRepository.findAllByOrderByTenAsc();
    }
}
