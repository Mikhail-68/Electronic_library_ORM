package ru.egorov.electroniclibrary.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.egorov.electroniclibrary.models.Author;
import ru.egorov.electroniclibrary.models.Validator.AuthorValidator;
import ru.egorov.electroniclibrary.repositories.AuthorRepository;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final AuthorValidator authorValidator;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, AuthorValidator authorValidator) {
        this.authorRepository = authorRepository;
        this.authorValidator = authorValidator;
    }

    @GetMapping("/new")
    public String showCreatePage(@ModelAttribute("author") Author author) {
        return "authors/new";
    }

    @PostMapping
    public String createAuthor(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "authors/new";
        }
        authorValidator.validate(author, bindingResult);
        if (bindingResult.hasErrors()) {
            return "authors/new";
        }
        authorRepository.save(author);
        return "redirect:/books/new";
    }

}
