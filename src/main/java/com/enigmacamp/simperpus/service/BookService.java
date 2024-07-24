package com.enigmacamp.simperpus.service;

import com.enigmacamp.simperpus.model.dto.request.BookRequest;
import com.enigmacamp.simperpus.model.dto.response.BookResponse;
import com.enigmacamp.simperpus.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookResponse> findAll();
    Optional<BookResponse> findById(String id);
    BookResponse save(BookRequest request);
    void deleteById(String id);
    BookResponse update(String id, BookRequest request);
    List<Book> findByYears(int year);
}