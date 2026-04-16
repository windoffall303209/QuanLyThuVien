package com.quanlythuvien.repository;

import com.quanlythuvien.domain.ChamCong;
import com.quanlythuvien.domain.ChamCongId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamCongRepository extends JpaRepository<ChamCong, ChamCongId> {

    List<ChamCong> findByMaThuThuOrderByNgayLamViecDesc(String maThuThu);
}
