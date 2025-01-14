package com.devtiro.jdbcTest.dao;

import com.devtiro.jdbcTest.domain.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorDao {

    void create(Author author);

    Optional<Author> findOne(long l);

    List<Author> find();

    void update(Long id, Author author);

    void delete(long l);
}
