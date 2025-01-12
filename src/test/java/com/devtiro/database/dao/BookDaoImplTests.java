package com.devtiro.database.dao;

import com.devtiro.database.dao.impl.AuthorDaoImpl;
import com.devtiro.database.dao.impl.BookDaoImpl;
import com.devtiro.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
                eq("Insert into books ( isbn, title, authorId ) VALEUS (?,?,? ) "),
                eq("589-244-175"),
                eq("Karamazov Brothers"),
                eq("1L")
        );
    }
}