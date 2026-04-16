package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.NhaCungCap;
import com.quanlythuvien.repository.NhaCungCapRepository;
import com.quanlythuvien.service.NhaCungCapService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class NhaCungCapServiceImpl implements NhaCungCapService {

    private final NhaCungCapRepository nhaCungCapRepository;

    public NhaCungCapServiceImpl(NhaCungCapRepository nhaCungCapRepository) {
        this.nhaCungCapRepository = nhaCungCapRepository;
    }

    @Override
    public List<NhaCungCap> findAll() {
        return nhaCungCapRepository.findAllByOrderByTenNccAsc();
    }
}
