package ru.egorov.electroniclibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.egorov.electroniclibrary.dao.BookDAO;
import ru.egorov.electroniclibrary.dao.RentDAO;
import ru.egorov.electroniclibrary.services.BookService;
import ru.egorov.electroniclibrary.services.RentService;

@Controller
@RequestMapping("/rents")
public class RentController {

    private final RentService rentService;
    private final BookService bookService;

    @Autowired
    public RentController(RentService rentService, BookService bookService) {
        this.rentService = rentService;
        this.bookService = bookService;
    }

    @PostMapping("/new")
    public String createRent(@RequestParam("clientSelect")int clientId,
                             @RequestParam("book") int bookId) {
        if(bookService.get(bookId).getRemainder() > 0)
            rentService.add(clientId, bookId);
        else throw new RuntimeException("This book missing in database");
        return "redirect:/books/" + bookId;
    }

    @PatchMapping("/{id}/edit")
    public String confirmReturnBook(@PathVariable("id") int rentId,
                                    @RequestParam(value = "typePage", required = false) String typePage,
                                    @RequestParam(value = "pageId", required = false) int pageId) {
        rentService.confirmReturnBook(rentId);

        if(typePage != null){
            if(typePage.equals("book"))
                return "redirect:/books/" + pageId;
            else if(typePage.equals("client"))
                return "redirect:/clients/" + pageId;
        }
        return "redirect:/";
    }
}

