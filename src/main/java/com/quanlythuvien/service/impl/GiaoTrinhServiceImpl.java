package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.GiaoTrinh;
import com.quanlythuvien.repository.GiaoTrinhRepository;
import com.quanlythuvien.service.GiaoTrinhService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GiaoTrinhServiceImpl implements GiaoTrinhService {

    private final GiaoTrinhRepository giaoTrinhRepository;

    public GiaoTrinhServiceImpl(GiaoTrinhRepository giaoTrinhRepository) {
        this.giaoTrinhRepository = giaoTrinhRepository;
    }

    @Override
    public List<GiaoTrinh> findAll() {
        return giaoTrinhRepository.findAllByOrderByMonHocAsc();
    }
}
