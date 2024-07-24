package com.enigmacamp.simperpus.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor

public class BorrowingResponse {
    private String id;
    private String bookId;
    private String memberId;
    private Date borrowedDate;
    private Date returnedDate;
    private boolean isReturned;

    public BorrowingResponse(String id, Date borrowedDate, Date returnedDate, boolean returned) {
        this.id = id;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
        this.isReturned = returned;
    }

    public BorrowingResponse(String s, String s1, String s2) {
        this.id = s;
        this.bookId = s1;
        this.memberId = s2;
    }

    public BorrowingResponse(String id, String bookId, String memberId, Date borrowedDate, Date returnedDate, boolean isReturned) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
        this.isReturned = isReturned;
    }
}