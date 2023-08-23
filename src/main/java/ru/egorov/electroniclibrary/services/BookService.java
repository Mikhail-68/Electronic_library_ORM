package ru.egorov.electroniclibrary.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorov.electroniclibrary.dao.BookDAO;
import ru.egorov.electroniclibrary.models.Book;

import java.util.List;

@Service
@Transactional
public class BookService {
    private final BookDAO bookDAO;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> getAll(){
        return bookDAO.getAll();
    }

    public Book get(int id){
        return bookDAO.get(id);
    }

    public void add(Book book){
        bookDAO.add(book);
    }

    public void update(int id, Book book){
        book.setId(id);
        bookDAO.update(book);
    }

    public void delete(int id){
        bookDAO.delete(id);
    }

}
