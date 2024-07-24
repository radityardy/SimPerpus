package com.enigmacamp.simperpus.service;

import com.enigmacamp.simperpus.model.dto.response.BorrowingResponse;
import com.enigmacamp.simperpus.model.entity.Borrowing;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BorrowingService {
    List<Borrowing> findAll();
    Optional<Borrowing> findById(String id);
    Borrowing save(Borrowing borrowing);
    void deleteById(String id);
    BorrowingResponse findBorrowingByMonth(int month);
    Borrowing update(String id, Borrowing borrowing);
}