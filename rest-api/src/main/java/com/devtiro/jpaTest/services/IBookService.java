package com.devtiro.jpaTest.services;

import com.devtiro.jpaTest.domain.entities.BookEntity;

public interface IBookService {

    BookEntity createBook(String isbn, BookEntity book) ;
}
