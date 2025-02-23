package com.devtiro.jpaTest.services;

import com.devtiro.jpaTest.domain.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface IBookService {

    BookEntity save(String isbn, BookEntity book) ;

    List<BookEntity> findAll();

    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findOne(String isbn);

    Boolean isExists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);

    BookEntity delete(String isbn);
}
