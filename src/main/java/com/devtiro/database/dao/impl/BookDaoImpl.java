package com.devtiro.database.dao.impl;

import com.devtiro.database.dao.IBookDao;
import com.devtiro.database.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements IBookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "Insert into books ( isbn, title, authorId ) VALUES (?,?,? ) ",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId()
        );
    }
}
