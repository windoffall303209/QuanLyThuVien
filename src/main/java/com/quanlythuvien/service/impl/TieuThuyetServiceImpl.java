package com.quanlythuvien.service.impl;

import com.quanlythuvien.domain.TieuThuyet;
import com.quanlythuvien.repository.TieuThuyetRepository;
import com.quanlythuvien.service.TieuThuyetService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TieuThuyetServiceImpl implements TieuThuyetService {

    private final TieuThuyetRepository tieuThuyetRepository;

    public TieuThuyetServiceImpl(TieuThuyetRepository tieuThuyetRepository) {
        this.tieuThuyetRepository = tieuThuyetRepository;
    }

    @Override
    public List<TieuThuyet> findAll() {
        return tieuThuyetRepository.findAllByOrderByTheLoaiAsc();
    }
}
