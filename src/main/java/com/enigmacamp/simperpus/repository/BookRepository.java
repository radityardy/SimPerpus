package com.enigmacamp.simperpus.repository;

import com.enigmacamp.simperpus.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String>  {
    @Query(value = "SELECT * FROM books WHERE years = :year", nativeQuery = true)
    List<Book> findByYears(int year);
}