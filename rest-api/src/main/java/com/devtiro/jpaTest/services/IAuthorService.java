package com.devtiro.jpaTest.services;

import com.devtiro.jpaTest.domain.entities.AuthorEntity;
import com.devtiro.jpaTest.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {

    AuthorEntity createAuthor(AuthorEntity author);

    Optional<AuthorEntity> findOne(Long id);

    List<AuthorEntity> findAll();
}
