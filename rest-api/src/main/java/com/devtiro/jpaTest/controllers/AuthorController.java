package com.devtiro.jpaTest.controllers;

import com.devtiro.jpaTest.domain.Author;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @PostMapping(path = "/authors")
    public Author createAuthor(@RequestBody Author author) {


    }


}
