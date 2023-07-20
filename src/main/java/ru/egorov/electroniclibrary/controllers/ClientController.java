package ru.egorov.electroniclibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egorov.electroniclibrary.dao.ClientDAO;
import ru.egorov.electroniclibrary.models.Client;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientDAO clientDAO;

    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @GetMapping
    public String showListClients(Model model) {
        model.addAttribute("clients", clientDAO.getAll());
        return "clients/index";
    }

    @GetMapping("/{id}")
    public String showClientInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientDAO.get(id));
        return "clients/client";
    }

    // Create

    @GetMapping("/new")
    public String showCreatePage(@ModelAttribute("client") Client client) {
        return "clients/new";
    }

    @PostMapping("/new")
    public String createClient(@ModelAttribute("client") Client client) {
        clientDAO.add(client);
        return "redirect:/clients";
    }


    // edit

    @GetMapping("/{id}/edit")
    public String showEditPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientDAO.get(id));
        return "clients/edit";
    }

    @PatchMapping("/{id}")
    public String updateClient(@PathVariable("id") int id, @ModelAttribute("client") Client client) {
        client.setId(id);
        clientDAO.update(client);
        return "redirect:/clients";
    }

    // delete

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable("id") int id) {
        clientDAO.delete(id);
        return "redirect:/clients";
    }
}
