package ru.egorov.electroniclibrary.dao.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.egorov.electroniclibrary.models.Book;
import ru.egorov.electroniclibrary.repositories.AuthorRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookMapper implements RowMapper<Book> {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setTitle(rs.getString("title"));
        book.setYearPublication(rs.getInt("year_publication"));
        book.setAmount(rs.getInt("amount"));
        book.setPricePerDay(rs.getInt("price_per_day"));
        book.setRemainder(rs.getInt("remainder"));

//        System.out.println(authorDAO);

        book.setAuthor(authorRepository.findById(rs.getInt("author_id")).orElse(null));

        return book;
    }
}
