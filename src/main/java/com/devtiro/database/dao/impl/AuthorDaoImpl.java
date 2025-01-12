package com.devtiro.database.dao.impl;

import com.devtiro.database.dao.IAuthorDao;
import com.devtiro.database.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoImpl implements IAuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update("Insert into authors ( id, name, age ) VALUES (?,?,? ) ",
                author.getId(),
                author.getName(),
                author.getAge()
        );
    }
}
