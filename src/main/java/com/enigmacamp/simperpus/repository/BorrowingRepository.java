package com.enigmacamp.simperpus.repository;

import com.enigmacamp.simperpus.model.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, String> {
    @Query(value = "SELECT * FROM borrowing WHERE EXTRACT(MONTH FROM borrowed_date) = :month", nativeQuery = true)
    List<Borrowing> findAllByBorrowedDateMonth(int month);
}
