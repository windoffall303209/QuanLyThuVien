package com.quanlythuvien.repository;

import com.quanlythuvien.domain.Reader;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// Truy cập dữ liệu độc giả trong cơ sở dữ liệu.
public interface ReaderRepository extends JpaRepository<Reader, Long> {

    @Query("""
        select r from Reader r
        where (:keyword is null or trim(:keyword) = ''
            or lower(r.readerCode) like lower(concat('%', :keyword, '%'))
            or lower(r.fullName) like lower(concat('%', :keyword, '%')))
        order by r.fullName asc
        """)
    List<Reader> search(@Param("keyword") String keyword);

    Optional<Reader> findByReaderCode(String readerCode);

    boolean existsByReaderCode(String readerCode);

    long countByActiveTrue();
}
