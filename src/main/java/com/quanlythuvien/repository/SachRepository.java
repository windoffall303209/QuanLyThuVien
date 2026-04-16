package com.quanlythuvien.repository;

import com.quanlythuvien.domain.Sach;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SachRepository extends JpaRepository<Sach, String> {

    @Query("""
        select s from Sach s
        where (:keyword is null or trim(:keyword) = ''
            or lower(s.maSach) like lower(concat('%', :keyword, '%'))
            or lower(s.tenSach) like lower(concat('%', :keyword, '%'))
            or lower(s.tacGia) like lower(concat('%', :keyword, '%')))
        order by s.tenSach asc
        """)
    List<Sach> search(@Param("keyword") String keyword);
}
