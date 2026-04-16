package com.quanlythuvien.repository;

import com.quanlythuvien.domain.NhaCungCap;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, String> {

    List<NhaCungCap> findAllByOrderByTenNccAsc();
}
