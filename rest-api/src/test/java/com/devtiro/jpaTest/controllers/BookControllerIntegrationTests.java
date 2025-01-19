package com.devtiro.jpaTest.controllers;

import com.devtiro.jpaTest.TestDataUtil;
import com.devtiro.jpaTest.domain.dto.BookDto;
import com.devtiro.jpaTest.domain.entities.BookEntity;
import com.devtiro.jpaTest.services.IBookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final IBookService bookService;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, IBookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }

    @Test
    public void testThatCreateBookReturnsHttpStatus201Created() throws Exception {
        BookDto bookDto = TestDataUtil.craeteTestBookDto(null);
        String crateBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(crateBookJson)
        ).andExpect(

                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateBookReturnsCreateBook() throws Exception {
        BookDto bookDto = TestDataUtil.craeteTestBookDto(null);
        String crateBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(crateBookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );
    }

    @Test
    public void testThatGetBookReturnsHttpStatus200Ok() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListBookReturnsBook() throws Exception {

        BookEntity testBookEntity = TestDataUtil.createTestBookEntityA(null );
        bookService.createBook(testBookEntity.getIsbn(), testBookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value("589-244-175")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value("Karamazov Brothers")
        );

    }

    @Test
    public void testThatGetBookReturnsHttpStatus200WhenBookExist() throws Exception {

        BookEntity testBookEntity = TestDataUtil.createTestBookEntityA(null );
        bookService.createBook(testBookEntity.getIsbn(), testBookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+ testBookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetBookReturnsHttpStatus404WhenNoBookExist() throws Exception {

        BookEntity testBookEntity = TestDataUtil.createTestBookEntityA(null );
        bookService.createBook(testBookEntity.getIsbn(), testBookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+ "adasfsdfa")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }


}
