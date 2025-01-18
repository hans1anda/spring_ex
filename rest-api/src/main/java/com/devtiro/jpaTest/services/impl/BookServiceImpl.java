package com.devtiro.jpaTest.services.impl;

import com.devtiro.jpaTest.domain.entities.BookEntity;
import com.devtiro.jpaTest.repositories.BookRepository;
import com.devtiro.jpaTest.services.IBookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
}
