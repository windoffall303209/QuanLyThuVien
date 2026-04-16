package com.quanlythuvien.repository;

import com.quanlythuvien.domain.ChiNhanh;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChiNhanhRepository extends JpaRepository<ChiNhanh, String> {

    List<ChiNhanh> findAllByOrderByTenAsc();
}
