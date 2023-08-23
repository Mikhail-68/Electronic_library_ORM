package ru.egorov.electroniclibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.egorov.electroniclibrary.dao.BookDAO;

@Controller
@RequestMapping("/books/search")
public class SearchBookController {
    private final BookDAO bookDAO;

    public SearchBookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String showSearchPage(@RequestParam(name = "request", required = false) String req,
                                 Model model) {
        model.addAttribute("request", req);
        model.addAttribute("books", bookDAO.findLikeTitle(req));
        return "books/search";
    }


}
