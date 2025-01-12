package com.devtiro.database.dao.impl;

import com.devtiro.database.domain.Book;
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
public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql() {

        Book book = Book.builder()
                .isbn("589-244-175")
                .title("Karamazov Brothers")
                .authorId(1L).build();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("Insert into books ( isbn, title, authorId ) VALUES (?,?,? )"),
                eq("589-244-175"),
                eq("Karamazov Brothers"),
                eq(1L)
        );
    }

    @Test
    public void testThatFindOneBookGeneratesTheCorrectSql() {

        //Call the findOne() method with an ID of 1L.
        underTest.findOne("589-244-175");

        // Verify that the correct SQL query was sent to the jdbcTemplate.
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, authorId from books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("589-244-175")
        );
    }
}