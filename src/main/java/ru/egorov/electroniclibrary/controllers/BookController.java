package ru.egorov.electroniclibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egorov.electroniclibrary.dao.BookDAO;
import ru.egorov.electroniclibrary.models.Book;
import ru.egorov.electroniclibrary.services.AuthorService;
import ru.egorov.electroniclibrary.services.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public BookController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public String showListBooks(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.get(id));
        return "books/book";
    }

    // Create

    @GetMapping("/new")
    public String showCreatePage(@ModelAttribute("book") Book book, Model model) {
        model.addAttribute("authors", authorService.findAll(Sort.by("name")));
        return "books/new";
    }

    @PostMapping("/new")
    public String createBook(@ModelAttribute("book") Book book) {
        bookService.add(book);
        return "redirect:/books";
    }

    // Update

    @GetMapping("/{id}/edit")
    public String showEditPage(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.get(id));
        model.addAttribute("authors", authorService.findAll(Sort.by("name")));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("book") Book book){
        bookService.update(id, book);
        return "redirect:/books";
    }

    // Delete

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/books";
    }


}
