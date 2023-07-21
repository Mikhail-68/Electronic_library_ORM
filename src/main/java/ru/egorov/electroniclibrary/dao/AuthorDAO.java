package ru.egorov.electroniclibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.egorov.electroniclibrary.dao.mapper.AuthorMapper;
import ru.egorov.electroniclibrary.models.Author;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Author> getAll() {
        return jdbcTemplate.query("SELECT * FROM author ORDER BY name", new AuthorMapper());
    }

    public Optional<Author> get(int id) {
        return jdbcTemplate.query("SELECT * FROM author WHERE author_id=?", new AuthorMapper(), id).stream().findAny();
    }

    public Optional<Author> get(String name) {
        return jdbcTemplate.query("SELECT * FROM author WHERE name=?", new AuthorMapper(), name)
                .stream().findAny();
    }

    public void add(Author author) {
        jdbcTemplate.update("INSERT INTO author (name) VALUES (?)", author.getName());
    }



}
