package com.quanlythuvien.repository;

import com.quanlythuvien.domain.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// Truy cập dữ liệu sách trong cơ sở dữ liệu.
public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = "category")
    @Query("""
        select b from Book b
        where (:keyword is null or trim(:keyword) = ''
            or lower(b.title) like lower(concat('%', :keyword, '%'))
            or lower(b.author) like lower(concat('%', :keyword, '%'))
            or lower(b.isbn) like lower(concat('%', :keyword, '%')))
        order by b.title asc
        """)
    List<Book> search(@Param("keyword") String keyword);

    @EntityGraph(attributePaths = "category")
    List<Book> findAllByOrderByTitleAsc();

    Optional<Book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    long countByActiveTrue();

    long countByCategoryIdAndActiveTrue(Long categoryId);

    @Query("select coalesce(sum(b.availableCopies), 0) from Book b where b.active = true")
    long sumAvailableCopies();
}
