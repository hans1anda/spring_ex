package com.devtiro.jpaTest.services;

import com.devtiro.jpaTest.domain.dto.AuthorDto;
import com.devtiro.jpaTest.domain.entities.AuthorEntity;

public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity author) ;

}
