package com.devtiro.jpaTest.services;

import com.devtiro.jpaTest.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    BookEntity save(String isbn, BookEntity book) ;

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    Boolean isExists(String isbn);
}
