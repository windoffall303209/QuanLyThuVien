package com.quanlythuvien.repository;

import com.quanlythuvien.domain.GiaoTrinh;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiaoTrinhRepository extends JpaRepository<GiaoTrinh, String> {

    List<GiaoTrinh> findAllByOrderByMonHocAsc();
}
