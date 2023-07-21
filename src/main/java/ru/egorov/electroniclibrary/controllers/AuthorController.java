package ru.egorov.electroniclibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.egorov.electroniclibrary.dao.AuthorDAO;
import ru.egorov.electroniclibrary.models.Author;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorDAO authorDAO;

    public AuthorController(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @GetMapping("/new")
    public String showCreatePage(@ModelAttribute("author") Author author) {
        return "authors/new";
    }

    @PostMapping()
    public String createAuthor(@ModelAttribute("author") Author author){
        authorDAO.add(author);
        return "redirect:/books/new";
    }

}
