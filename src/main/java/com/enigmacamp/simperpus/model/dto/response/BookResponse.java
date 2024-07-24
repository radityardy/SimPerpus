package com.enigmacamp.simperpus.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private String id;
    private String title;
    private String author;
    private Integer years;
    private boolean available;
}