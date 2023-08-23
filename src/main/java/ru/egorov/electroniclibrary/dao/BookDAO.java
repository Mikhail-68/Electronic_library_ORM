package ru.egorov.electroniclibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.egorov.electroniclibrary.dao.mapper.BookMapper;
import ru.egorov.electroniclibrary.models.Book;

import java.util.Collections;
import java.util.List;

@Repository
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;
    private final BookMapper bookMapper;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate, BookMapper bookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookMapper = bookMapper;
    }

    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * FROM book ORDER BY title", bookMapper);
    }

    public Book get(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", bookMapper, id).stream().findAny().orElse(null);
    }

    public void add(Book book) {
        jdbcTemplate.update("INSERT INTO book (isbn, title, author_id, year_publication, amount) VALUES (?,?,?,?,?)",
                book.getIsbn(), book.getTitle(), book.getAuthor().getId(), book.getYearPublication(), book.getAmount());
    }

    public void update(Book book) {
        jdbcTemplate.update("UPDATE book SET isbn=?, title=?, author_id=?, year_publication=?, amount=? WHERE book_id=?",
                book.getIsbn(), book.getTitle(), book.getAuthor().getId(), book.getYearPublication(), book.getAmount(), book.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    public List<Book> findLikeTitle(String request) {
        if(request == null)
            return Collections.emptyList();
        request = "%" + request + "%";
        return jdbcTemplate.query("SELECT * FROM book WHERE UPPER(title) LIKE ? ORDER BY title", bookMapper, request.toUpperCase());
    }

}
