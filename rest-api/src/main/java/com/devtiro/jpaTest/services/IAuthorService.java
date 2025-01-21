package com.devtiro.jpaTest.services;

import com.devtiro.jpaTest.domain.dto.AuthorDto;
import com.devtiro.jpaTest.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {

    AuthorEntity save(AuthorEntity author);

    Optional<AuthorEntity> findOne(Long id);

    List<AuthorEntity> findAll();

    boolean isExists(Long id);

    AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);

    void delete(Long id);
}
