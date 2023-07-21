package ru.egorov.electroniclibrary.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.egorov.electroniclibrary.dao.AuthorDAO;
import ru.egorov.electroniclibrary.models.Author;
import ru.egorov.electroniclibrary.models.Validator.AuthorValidator;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorDAO authorDAO;
    private final AuthorValidator authorValidator;

    @Autowired
    public AuthorController(AuthorDAO authorDAO, AuthorValidator authorValidator) {
        this.authorDAO = authorDAO;
        this.authorValidator = authorValidator;
    }

    @GetMapping("/new")
    public String showCreatePage(@ModelAttribute("author") Author author) {
        return "authors/new";
    }

    @PostMapping
    public String createAuthor(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "authors/new";
        }
        authorValidator.validate(author, bindingResult);
        if(bindingResult.hasErrors()){
            return "authors/new";
        }
        authorDAO.add(author);
        return "redirect:/books/new";
    }

}
