package com.devtiro.database.dao.impl;

import com.devtiro.database.dao.IBookDao;
import com.devtiro.database.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements IBookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("Insert into books ( isbn, title, authorId ) VALUES (?,?,? )", book.getIsbn(), book.getTitle(), book.getAuthorId());
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> results = jdbcTemplate.query(
                "SELECT isbn, title, authorId from books WHERE isbn = ? LIMIT 1",
                new BookRowMapper(),
                isbn);

        return results.stream().findFirst();
    }

    public static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder().
                    isbn(rs.getString("isbn")).
                    title(rs.getString("title")).
                    authorId(rs.getLong("authorId"))
                    .build();
        }
    }
}
