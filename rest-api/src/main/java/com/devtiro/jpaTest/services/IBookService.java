package com.devtiro.jpaTest.services;

import com.devtiro.jpaTest.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    BookEntity createBook(String isbn, BookEntity book) ;

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);
}
