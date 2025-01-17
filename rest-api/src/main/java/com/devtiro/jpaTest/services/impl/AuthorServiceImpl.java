package com.devtiro.jpaTest.services.impl;

import com.devtiro.jpaTest.domain.entities.AuthorEntity;
import com.devtiro.jpaTest.repositories.AuthorRepository;
import com.devtiro.jpaTest.services.AuthorService;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity author) {
        return authorRepository.save(author);
    }
}
