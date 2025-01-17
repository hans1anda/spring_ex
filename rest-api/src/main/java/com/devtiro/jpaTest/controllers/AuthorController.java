package com.devtiro.jpaTest.controllers;

import com.devtiro.jpaTest.domain.dto.AuthorDto;
import com.devtiro.jpaTest.services.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(path = "/authors")
    public AuthorDto createAuthor(@RequestBody AuthorDto author) {
        return authorService.createAuthor(author);
    }


}
