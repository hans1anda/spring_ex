package com.devtiro.jdbcTest.dao.impl;

import com.devtiro.jdbcTest.dao.IBookDao;
import com.devtiro.jdbcTest.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements IBookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("Insert into books (isbn, title, author_id ) VALUES (?,?,? )",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId());
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> results = jdbcTemplate.query(
                "SELECT isbn, title, author_id from books WHERE isbn = ? LIMIT 1",
                new BookRowMapper(),
                isbn);

        return results.stream().findFirst();
    }

    @Override
    public List<Book> find() {
        return jdbcTemplate.query("Select isbn, title, author_id from books",
                new BookRowMapper());
    }

    @Override
    public void update(String isbn, Book book) {
        jdbcTemplate.update("Update books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId(),
                isbn);
    }

    @Override
    public void delete(String s) {
        jdbcTemplate.update("Delete from books where isbn = ?", s);
    }

    public static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder().
                    isbn(rs.getString("isbn")).
                    title(rs.getString("title")).
                    authorId(rs.getLong("author_id"))
                    .build();
        }
    }
}