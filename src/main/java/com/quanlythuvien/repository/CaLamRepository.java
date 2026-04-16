package com.quanlythuvien.repository;

import com.quanlythuvien.domain.CaLam;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaLamRepository extends JpaRepository<CaLam, String> {

    List<CaLam> findAllByOrderByMaCaLamAsc();
}
