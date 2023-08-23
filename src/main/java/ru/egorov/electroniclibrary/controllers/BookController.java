package ru.egorov.electroniclibrary.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egorov.electroniclibrary.models.Book;
import ru.egorov.electroniclibrary.models.Validator.BookValidator;
import ru.egorov.electroniclibrary.services.AuthorService;
import ru.egorov.electroniclibrary.services.BookService;
import ru.egorov.electroniclibrary.services.ClientService;
import ru.egorov.electroniclibrary.services.RentService;

@Controller
@RequestMapping("/books")
public class BookController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final ClientService clientService;
    private final RentService rentService;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(AuthorService authorService, BookService bookService, ClientService clientService, RentService rentService, BookValidator bookValidator) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.clientService = clientService;
        this.rentService = rentService;
        this.bookValidator = bookValidator;
    }

    @GetMapping
    public String showListBooks(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.get(id));
        model.addAttribute("rents", rentService.getByBook(id));
        model.addAttribute("clients", clientService.findAll());
        return "books/book";
    }

    // Create

    @GetMapping("/new")
    public String showCreatePage(@ModelAttribute("book") Book book, Model model) {
        model.addAttribute("authors", authorService.findAll(Sort.by("name")));
        return "books/new";
    }

    @PostMapping("/new")
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            return "books/new";
        }
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            return "books/new";
        }

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
    public String updateBook(@PathVariable("id") int id,
                             @ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult,
                             Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            return "books/edit";
        }
        bookValidator.validate(book, bindingResult);
//        System.out.println(bindingResult.getFieldErrors());
        if (bindingResult.getFieldErrors().stream().filter(x -> !x.getField().equals("isbn")).findAny().isPresent()) {
            model.addAttribute("authors", authorService.findAll());
            return "books/edit";
        }

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
