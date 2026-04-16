package com.quanlythuvien.repository;

import com.quanlythuvien.domain.TieuThuyet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TieuThuyetRepository extends JpaRepository<TieuThuyet, String> {

    List<TieuThuyet> findAllByOrderByTheLoaiAsc();
}
