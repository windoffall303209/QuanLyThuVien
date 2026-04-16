package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.ThuThu;
import com.quanlythuvien.repository.ThuThuRepository;
import com.quanlythuvien.service.ThuThuService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ThuThuServiceImpl implements ThuThuService {

    private final ThuThuRepository thuThuRepository;

    public ThuThuServiceImpl(ThuThuRepository thuThuRepository) {
        this.thuThuRepository = thuThuRepository;
    }

    @Override
    public List<ThuThu> findAll() {
        return thuThuRepository.findAll().stream()
                .sorted((a, b) -> a.getHoTen().compareToIgnoreCase(b.getHoTen()))
                .toList();
    }
}
