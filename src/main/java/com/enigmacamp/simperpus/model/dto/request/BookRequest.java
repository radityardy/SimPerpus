package com.enigmacamp.simperpus.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String id;
    private String title;
    private String author;
    private Integer years;
    private int availableCopies;
}