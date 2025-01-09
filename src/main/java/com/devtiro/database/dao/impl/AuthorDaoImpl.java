package com.devtiro.database.dao.impl;

import com.devtiro.database.dao.IAuthorDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoImpl implements IAuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
