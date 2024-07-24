package com.enigmacamp.simperpus.controller;

import com.enigmacamp.simperpus.config.APIUrl;
import com.enigmacamp.simperpus.exception.ResourceNotFoundException;
import com.enigmacamp.simperpus.model.dto.request.BorrowingRequest;
import com.enigmacamp.simperpus.model.dto.response.BorrowingResponse;
import com.enigmacamp.simperpus.model.entity.Book;
import com.enigmacamp.simperpus.model.entity.Borrowing;
import com.enigmacamp.simperpus.model.entity.Member;
import com.enigmacamp.simperpus.service.BorrowingService;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(APIUrl.BORROWING_API)
public class BorrowingController {
    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping
    public List<BorrowingResponse> getBorrowingHistory() {
        return borrowingService.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BorrowingResponse getBorrowingById(@PathVariable String id) {
        return borrowingService.findById(id)
                .map(this::convertToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing not found with ID: " + id));
    }

    @GetMapping("/months/{month}")
    public BorrowingResponse getBorrowingByMonth(@PathVariable int month) {
        return borrowingService.findBorrowingByMonth(month);
    }

    @PostMapping
    public BorrowingResponse createBorrowing(@RequestBody BorrowingRequest borrowingRequest) {
        Borrowing borrowing = borrowingService.save(convertToEntity(borrowingRequest));
        if (borrowing == null) {
            throw new IllegalArgumentException("Failed to create borrowing");
        }
        return convertToResponse(borrowing);
    }

    @PutMapping("/{id}")
    public BorrowingResponse updateBorrowing(@PathVariable String id, @RequestBody BorrowingRequest updatedBorrowingRequest) {
        Borrowing updatedBorrowing = borrowingService.update(id, convertToEntity(updatedBorrowingRequest));
        if (updatedBorrowing == null) {
            throw new ResourceNotFoundException("Failed to update borrowing with ID: " + id);
        }
        return convertToResponse(updatedBorrowing);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrowing(@PathVariable String id) {
        borrowingService.deleteById(id);
    }

    private BorrowingResponse convertToResponse(Borrowing borrowing) {
        if (borrowing == null) {
            throw new IllegalArgumentException("Borrowing cannot be null");
        }
        return new BorrowingResponse(
                borrowing.getId().toString(),
                borrowing.getBook().getId(),
                borrowing.getMember().getId(),
                java.sql.Date.valueOf(borrowing.getBorrowedDate()),
                borrowing.getReturnedDate() != null ? java.sql.Date.valueOf(borrowing.getReturnedDate()) : null,
                borrowing.isReturned()
        );
    }

    private Borrowing convertToEntity(BorrowingRequest borrowingRequest) {
        Borrowing borrowing = new Borrowing();
        Book book = new Book();
        book.setId(borrowingRequest.getBookId());
        Member member = new Member();
        member.setId(borrowingRequest.getMemberId());
        borrowing.setBook(book);
        borrowing.setMember(member);
        borrowing.setBorrowedDate(borrowingRequest.getBorrowedDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        if (borrowingRequest.getReturnedDate() != null) {
            borrowing.setReturnedDate(borrowingRequest.getReturnedDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
        }
        borrowing.setReturned(borrowingRequest.isReturned());
        return borrowing;
    }
}