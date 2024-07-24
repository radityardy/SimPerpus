package com.enigmacamp.simperpus.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    private String id;
    private String name;
    private String email;
    private Date joinDate;
}
