package com.quanlythuvien.repository;

import com.quanlythuvien.domain.ThanhVien;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThanhVienRepository extends JpaRepository<ThanhVien, String> {

    @Query("""
        select tv from ThanhVien tv
        where (:keyword is null or trim(:keyword) = ''
            or lower(tv.maThanhVien) like lower(concat('%', :keyword, '%'))
            or lower(tv.hoTen) like lower(concat('%', :keyword, '%')))
        order by tv.hoTen asc
        """)
    List<ThanhVien> search(@Param("keyword") String keyword);

    List<ThanhVien> findByTinhTrangTheIgnoreCaseOrderByHoTenAsc(String tinhTrangThe);
}
