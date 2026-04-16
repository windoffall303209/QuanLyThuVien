package com.quanlythuvien.repository;

import com.quanlythuvien.domain.CungCap;
import com.quanlythuvien.domain.CungCapId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CungCapRepository extends JpaRepository<CungCap, CungCapId> {

    List<CungCap> findByMaNccOrderByMaChiNhanhAsc(String maNcc);
}
