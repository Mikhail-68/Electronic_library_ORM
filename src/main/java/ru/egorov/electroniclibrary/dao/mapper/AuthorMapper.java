package ru.egorov.electroniclibrary.dao.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import ru.egorov.electroniclibrary.dao.AuthorDAO;
import ru.egorov.electroniclibrary.models.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(rs.getInt("author_id"));
        author.setName(rs.getString("name"));
        return author;
    }
}
