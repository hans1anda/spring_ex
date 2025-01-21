package com.devtiro.jpaTest.controllers;


import com.devtiro.jpaTest.TestDataUtil;
import com.devtiro.jpaTest.domain.dto.AuthorDto;
import com.devtiro.jpaTest.domain.entities.AuthorEntity;
import com.devtiro.jpaTest.services.IAuthorService;
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

public class AuthorsControllerIntegrationTests {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final IAuthorService authorService;

    @Autowired
    public AuthorsControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, IAuthorService authorService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.authorService = authorService;
    }


    @Test
    public void testThatCreateAuthorSuccessfullyReturns201Created() throws Exception {

        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(

                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {

        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Hans Landa")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("59")
        );
    }


    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception {

        AuthorEntity testAuthorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Hans Landa")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value(59)
        );

    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus200WhenAuthorExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus404WhenNoAuthorExist() throws Exception {
        AuthorEntity testAuthorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFullAuthorReturnsHttpStatus404WhenNoAuthorExist() throws Exception {
        AuthorDto testAuthorDto = TestDataUtil.createTestAuthorDto();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFullAuthorReturnsHttpStatus200WhenAuthorExist() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthorEntity = authorService.save(testAuthorA);

        AuthorDto testAuthorDto = TestDataUtil.createTestAuthorDto();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorC();
        AuthorEntity savedAuthorEntity = authorService.save(authorEntity);

        AuthorEntity testAuthorEntity = TestDataUtil.createTestAuthorB();
        testAuthorEntity.setId(savedAuthorEntity.getId());

        String authorEntityUpdateJson = objectMapper.writeValueAsString(testAuthorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorEntityUpdateJson)

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthorEntity.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(testAuthorEntity.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(testAuthorEntity.getAge())
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsHttpStatus20Ok() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthorEntity = authorService.save(testAuthorA);

        AuthorDto testAuthorDto = TestDataUtil.createTestAuthorDto();
        testAuthorA.setName("UPDATED");
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsUpdatedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthorEntity = authorService.save(testAuthorA);

        AuthorDto testAuthorDto = TestDataUtil.createTestAuthorDto();
        testAuthorDto.setName("UPDATED");
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(testAuthorA.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("UPDATED")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(testAuthorDto.getAge())
        );
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForNonExistingAuthor() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForExistingAuthor() throws Exception {

        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthorEntity = authorService.save(testAuthorA);


        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/"+ savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
