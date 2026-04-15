package com.quanlythuvien.repository;

import com.quanlythuvien.domain.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// Truy cập dữ liệu thể loại trong cơ sở dữ liệu.
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
}
