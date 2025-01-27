package com.devtiro.jpaTest.controllers;


import com.devtiro.jpaTest.domain.dto.AuthorDto;
import com.devtiro.jpaTest.domain.dto.BookDto;
import com.devtiro.jpaTest.domain.entities.AuthorEntity;
import com.devtiro.jpaTest.domain.entities.BookEntity;
import com.devtiro.jpaTest.mappers.Mapper;
import com.devtiro.jpaTest.services.IBookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final Mapper<BookEntity, BookDto> bookMapper;

    private final IBookService bookService;


    public BookController(Mapper<BookEntity, BookDto> bookMapper, IBookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        Boolean bookExist = bookService.isExists(isbn);
        BookEntity savedBook = bookService.save(isbn, bookEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBook);

        if (!bookExist) {
            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(savedBookDto, HttpStatus.OK);
        }

    }

    @GetMapping(path = "/books/paged")
    public Page<BookDto> listBook(Pageable pageable) {
        Page<BookEntity> bookList = bookService.findAll(pageable);
        return bookList.map(bookMapper::mapTo);
    }

    @GetMapping(path = "/books")
    public List<BookDto> listBook() {
        List<BookEntity> bookList = bookService.findAll();
        return bookList.stream()
                .map(bookMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn) {
        Optional<BookEntity> foundBook = bookService.findOne(isbn);
        return foundBook.map(bookEntity -> {
            BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> partialUpdate(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        if(!bookService.isExists(isbn)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity updateBookEntity = bookService.partialUpdate(isbn, bookEntity);

        return new ResponseEntity<>(bookMapper.mapTo(updateBookEntity), HttpStatus.OK);

    }

    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable("isbn") String isbn) {
        if(!bookService.isExists(isbn)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookEntity deletedBookEntity = bookService.delete(isbn);
        BookDto deletedBookDto = bookMapper.mapTo(deletedBookEntity);

        return new ResponseEntity<>(deletedBookDto, HttpStatus.NO_CONTENT);
    }


}
