package com.quanlythuvien.repository;

import com.quanlythuvien.domain.BorrowRecord;
import com.quanlythuvien.domain.BorrowStatus;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// Truy cập dữ liệu phiếu mượn trong cơ sở dữ liệu.
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    @EntityGraph(attributePaths = {"reader", "book", "book.category"})
    @Query("select br from BorrowRecord br order by coalesce(br.returnDate, br.borrowDate) desc, br.id desc")
    List<BorrowRecord> findAllByLatestActivityDesc();

    @EntityGraph(attributePaths = {"reader", "book", "book.category"})
    @Query("select br from BorrowRecord br where br.reader.id = :readerId and br.status in :statuses order by br.dueDate asc, br.id desc")
    List<BorrowRecord> findByReaderIdAndStatusInOrderByDueDateAsc(@Param("readerId") Long readerId,
                                                                  @Param("statuses") List<BorrowStatus> statuses);

    @EntityGraph(attributePaths = {"reader", "book", "book.category"})
    @Query("select br from BorrowRecord br where br.reader.id = :readerId and br.status = 'RETURNED' order by br.returnDate desc, br.id desc")
    List<BorrowRecord> findReturnedByReaderIdOrderByReturnDateDesc(@Param("readerId") Long readerId);

    @EntityGraph(attributePaths = {"reader", "book", "book.category"})
    List<BorrowRecord> findByStatusInOrderByDueDateAsc(List<BorrowStatus> statuses);

    @EntityGraph(attributePaths = {"reader", "book", "book.category"})
    List<BorrowRecord> findByStatusOrderByDueDateAsc(BorrowStatus status);

    long countByStatus(BorrowStatus status);

    @EntityGraph(attributePaths = {"reader", "book", "book.category"})
    @Query("""
        select br from BorrowRecord br
        where br.status = 'OVERDUE' or (br.status = 'BORROWED' and br.dueDate < :today)
        order by br.dueDate asc
        """)
    List<BorrowRecord> findOverdue(@Param("today") LocalDate today);

    @EntityGraph(attributePaths = {"reader", "book", "book.category"})
    @Query("select br from BorrowRecord br order by br.createdAt desc")
    List<BorrowRecord> findRecent(Pageable pageable);

    boolean existsByBookIdAndStatusIn(Long bookId, List<BorrowStatus> statuses);

    @Modifying
    @Query("update BorrowRecord br set br.status = 'OVERDUE' where br.status = 'BORROWED' and br.dueDate < :today")
    int markOverdue(@Param("today") LocalDate today);
}
