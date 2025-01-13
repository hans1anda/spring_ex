package com.devtiro.database.dao.impl;

import com.devtiro.database.dao.IAuthorDao;
import com.devtiro.database.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements IAuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update("Insert into authors (id, name, age) VALUES (?,?,?)",
                author.getId(),
                author.getName(),
                author.getAge()
        );
    }

    @Override
    public Optional<Author> findOne(long authorId) {

        // Run a SQL query to fetch author details from the database.
        List<Author> results = jdbcTemplate.query(
                "Select id, name, age FROM authors WHERE id = ? LIMIT 1",
                new AuthorRowMapper(), authorId);

        //If a result is found, return it as an Optional.
        return results.stream().findFirst();
    }

    @Override
    public List<Author> find() {

        return jdbcTemplate.query(
                "select id, name, age FROM authors",
                new AuthorRowMapper()
        );
    }

    // INNER CLASS
    public static class AuthorRowMapper implements RowMapper<Author> {


        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder().
                    id(rs.getLong("id")).
                    name(rs.getString("name")).
                    age(rs.getInt("age")).
                    build();
        }
    }
}