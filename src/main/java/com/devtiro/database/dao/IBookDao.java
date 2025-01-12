package com.devtiro.database.dao;

import com.devtiro.database.domain.Book;

import java.util.Optional;

public interface IBookDao {

    void create(Book book);

    Optional<Book> findOne(String isbn);
}
