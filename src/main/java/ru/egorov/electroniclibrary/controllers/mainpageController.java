package ru.egorov.electroniclibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class mainpageController {
    @GetMapping
    public String showClients(){
        return "mainpage";
    }
}
