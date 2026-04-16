package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.CungCap;
import com.quanlythuvien.repository.CungCapRepository;
import com.quanlythuvien.service.CungCapService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CungCapServiceImpl implements CungCapService {

    private final CungCapRepository cungCapRepository;

    public CungCapServiceImpl(CungCapRepository cungCapRepository) {
        this.cungCapRepository = cungCapRepository;
    }

    @Override
    public List<CungCap> findAll() {
        return cungCapRepository.findAll();
    }
}
