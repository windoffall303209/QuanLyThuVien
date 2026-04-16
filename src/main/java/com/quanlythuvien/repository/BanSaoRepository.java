package com.quanlythuvien.repository;

import com.quanlythuvien.domain.BanSao;
import com.quanlythuvien.domain.BanSaoId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanSaoRepository extends JpaRepository<BanSao, BanSaoId> {

    List<BanSao> findByMaSachOrderByCopyNoAsc(String maSach);

    List<BanSao> findByTinhTrangMuonOrderByNgayNhapDesc(String tinhTrangMuon);

    long countByTinhTrangMuonIgnoreCase(String tinhTrangMuon);
}
