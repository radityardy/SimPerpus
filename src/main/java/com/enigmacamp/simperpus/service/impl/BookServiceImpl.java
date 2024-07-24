package com.enigmacamp.simperpus.service.impl;

import com.enigmacamp.simperpus.model.dto.request.BookRequest;
import com.enigmacamp.simperpus.model.dto.response.BookResponse;
import com.enigmacamp.simperpus.model.entity.Book;
import com.enigmacamp.simperpus.repository.BookRepository;
import com.enigmacamp.simperpus.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookResponse> findById(String id) {
        return bookRepository.findById(id)
                .map(this::convertToResponse);
    }

    @Override
    public BookResponse save(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublicationYear(bookRequest.getYears());
        book.setAvailableCopies(bookRequest.getAvailableCopies());
        Book savedBook = bookRepository.save(book);
        return convertToResponse(savedBook);
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponse update(String id, BookRequest request) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(request.getTitle());
            book.setAuthor(request.getAuthor());
            book.setPublicationYear(request.getYears());
            book.setAvailableCopies(request.getAvailableCopies());
            Book updatedBook = bookRepository.save(book);
            return convertToResponse(updatedBook);
        }
        return null;
    }

    @Override
    public List<Book> findByYears(int year) {
        return bookRepository.findByYears(year);
    }

    private BookResponse convertToResponse(Book book) {
        boolean available = book.getAvailableCopies() > 0;
        return new BookResponse(book.getId().toString(), book.getTitle(), book.getAuthor(), book.getPublicationYear(), available);
    }
}