package com.enigmacamp.simperpus.service.impl;

import com.enigmacamp.simperpus.model.dto.response.BorrowingResponse;
import com.enigmacamp.simperpus.model.entity.Borrowing;
import com.enigmacamp.simperpus.repository.BorrowingRepository;
import com.enigmacamp.simperpus.repository.MemberRepository;
import com.enigmacamp.simperpus.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {
    private final MemberRepository memberRepository;
    private final BorrowingRepository borrowingRepository;

    @Override
    public List<Borrowing> findAll() {
        return borrowingRepository.findAll();
    }

    @Override
    public Optional<Borrowing> findById(String id) {
        return borrowingRepository.findById(id);
    }

    @Override
    public Borrowing save(Borrowing borrowing) {
        if (borrowing.getMember() != null && borrowing.getMember().getId() == null) {
            memberRepository.save(borrowing.getMember());
        }
        return borrowingRepository.save(borrowing);
    }

    @Override
    public void deleteById(String id) {
        borrowingRepository.deleteById(id);
    }

    @Override
    public BorrowingResponse findBorrowingByMonth(int month) {
        List<Borrowing> borrowings = borrowingRepository.findAllByBorrowedDateMonth(month);
        int totalBorrowedBooks = borrowings.size();
        int totalReturnedBooks = (int) borrowings.stream()
                .filter(Borrowing::isReturned)
                .count();
        int totalNotReturnedBooks = totalBorrowedBooks - totalReturnedBooks;
        return new BorrowingResponse(
                String.valueOf(totalBorrowedBooks),
                String.valueOf(totalReturnedBooks),
                String.valueOf(totalNotReturnedBooks)
        );
    }

    @Override
    public Borrowing update(String id, Borrowing borrowing) {
        Optional<Borrowing> optionalBorrowing = borrowingRepository.findById(id);
        if (optionalBorrowing.isPresent()) {
            Borrowing existingBorrowing = optionalBorrowing.get();
            existingBorrowing.setBorrowedDate(borrowing.getBorrowedDate());
            existingBorrowing.setReturnedDate(borrowing.getReturnedDate());
            existingBorrowing.setReturned(borrowing.isReturned());
            return borrowingRepository.save(existingBorrowing);
        }
        return null;
    }
}