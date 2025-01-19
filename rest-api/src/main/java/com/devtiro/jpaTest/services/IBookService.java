package com.devtiro.jpaTest.services;

import com.devtiro.jpaTest.domain.entities.BookEntity;

import java.util.List;

public interface IBookService {

    BookEntity createBook(String isbn, BookEntity book) ;

    List<BookEntity> findAll();
}
