package com.devtiro.database.dao.impl;

import com.devtiro.database.dao.IBookDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements IBookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
