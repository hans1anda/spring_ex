package com.devtiro.database.dao.impl;

import com.devtiro.database.TestDataUtil;
import com.devtiro.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {

        Author author = TestDataUtil.createTestAuthorA();

        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("Insert into authors (id, name, age) VALUES (?,?,?)"),
                eq(1L),
                eq("Hans Landa"),
                eq(80)
        );
    }

    @Test
    public void testThatFindOneAuthorGeneratesTheCorrectSql() {

        //Call the findOne() method with an ID of 1L.
        underTest.findOne(1L);

        // Verify that the correct SQL query was sent to the jdbcTemplate.
        verify(jdbcTemplate).query(
                eq("Select id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L));
    }

    @Test
    public void tesThatFindManyGeneratesCorrectSql() {

        underTest.find();

        verify(jdbcTemplate).query(eq("select id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );

    }

}
