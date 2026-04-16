package com.quanlythuvien.repository;

import com.quanlythuvien.domain.ThuThu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThuThuRepository extends JpaRepository<ThuThu, String> {

    List<ThuThu> findByMaChiNhanhOrderByHoTenAsc(String maChiNhanh);
}
