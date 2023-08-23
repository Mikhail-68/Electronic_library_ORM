package ru.egorov.electroniclibrary.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egorov.electroniclibrary.models.Client;
import ru.egorov.electroniclibrary.models.Validator.ClientValidator;
import ru.egorov.electroniclibrary.services.ClientService;
import ru.egorov.electroniclibrary.services.RentService;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientValidator clientValidator;
    private final RentService rentService;

    @Autowired
    public ClientController(ClientService clientService, ClientValidator clientValidator, RentService rentService) {
        this.clientService = clientService;
        this.clientValidator = clientValidator;
        this.rentService = rentService;
    }

    @GetMapping
    public String showListClients(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "clients/index";
    }

    @GetMapping("/{id}")
    public String showClientInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientService.findById(id));
        model.addAttribute("rents", rentService.getByClient(id));
        return "clients/client";
    }

    // Create

    @GetMapping("/new")
    public String showCreatePage(@ModelAttribute("client") Client client) {
        return "clients/new";
    }

    @PostMapping("/new")
    public String createClient(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "clients/new";
        clientValidator.validate(client, bindingResult);
        if(bindingResult.hasErrors())
            return "clients/new";

        clientService.save(client);
        return "redirect:/clients";
    }

    // edit

    @GetMapping("/{id}/edit")
    public String showEditPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientService.findById(id));
        return "clients/edit";
    }

    @PatchMapping("/{id}")
    public String updateClient(@PathVariable("id") int id,
                               @ModelAttribute("client") @Valid Client client,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "/clients/edit";
        clientValidator.validate(client, bindingResult);
        if(bindingResult.hasErrors())
            return "/clients/edit";

        clientService.update(id, client);
        return "redirect:/clients";
    }

    // delete

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable("id") int id) {
        clientService.deleteById(id);
        return "redirect:/clients";
    }
}
