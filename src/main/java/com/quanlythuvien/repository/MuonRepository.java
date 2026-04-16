package com.quanlythuvien.repository;

import com.quanlythuvien.domain.Muon;
import com.quanlythuvien.domain.MuonId;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MuonRepository extends JpaRepository<Muon, MuonId> {

    List<Muon> findByNgayTraIsNullOrderByNgayHenTraAsc();

    List<Muon> findByNgayTraIsNotNullOrderByNgayTraDesc();

    List<Muon> findTop5ByOrderByNgayMuonDesc();

    @Query("""
        select m from Muon m
        where m.ngayTra is null and m.ngayHenTra < :today
        order by m.ngayHenTra asc
        """)
    List<Muon> findOverdue(LocalDate today);

    List<Muon> findByMaThanhVienOrderByNgayMuonDesc(String maThanhVien);
}
