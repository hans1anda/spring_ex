package com.devtiro.jpaTest.services;

import com.devtiro.jpaTest.domain.entities.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {

    AuthorEntity save(AuthorEntity author);

    Optional<AuthorEntity> findOne(Long id);

    List<AuthorEntity> findAll();

    Page<AuthorEntity> findAll(Pageable pageable);

    boolean isExists(Long id);

    AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);

    AuthorEntity delete(Long id);
}
