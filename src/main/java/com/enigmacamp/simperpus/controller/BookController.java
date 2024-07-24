package com.enigmacamp.simperpus.controller;

import com.enigmacamp.simperpus.config.APIUrl;
import com.enigmacamp.simperpus.model.dto.request.BookRequest;
import com.enigmacamp.simperpus.model.dto.response.BookResponse;
import com.enigmacamp.simperpus.model.entity.Book;
import com.enigmacamp.simperpus.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.BOOK_API)
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping(path = "/{id}")
    public BookResponse getBookById(@PathVariable String id) {
        return bookService.findById(id).orElse(null);
    }

    @GetMapping(path = "/years/{years}")
    public List<BookResponse> getBooksByPublicationYear(@PathVariable int years) {
        return bookService.findByYears(years).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    public BookResponse createBook(@RequestBody BookRequest bookRequest) {
        return bookService.save(bookRequest);
    }

    @PutMapping(path = "/{id}")
    public BookResponse updateBook(@PathVariable String id, @RequestBody BookRequest updatedBookRequest) {
        return bookService.update(id, updatedBookRequest);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteById(id);
    }

    private BookResponse convertToResponse(Book book) {
        boolean available = book.getAvailableCopies() > 0;
        return new BookResponse(book.getId().toString(), book.getTitle(), book.getAuthor(), book.getPublicationYear(), available);
    }
}