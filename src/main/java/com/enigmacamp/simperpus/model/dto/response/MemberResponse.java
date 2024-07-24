package com.enigmacamp.simperpus.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.text.SimpleDateFormat;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private String id;
    private String name;
    private String email;
    private String joinDate;
    private Set<BorrowingResponse> borrowings;

    public void setJoinDate(Date joinDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.joinDate = formatter.format(joinDate);
    }
}