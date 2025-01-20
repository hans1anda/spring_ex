package com.devtiro.jpaTest.services.impl;

import com.devtiro.jpaTest.domain.entities.BookEntity;
import com.devtiro.jpaTest.repositories.BookRepository;
import com.devtiro.jpaTest.services.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity save(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
            return bookRepository.findById(isbn);
    }

    @Override
    public Boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }
}
