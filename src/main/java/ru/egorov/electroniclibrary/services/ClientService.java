package ru.egorov.electroniclibrary.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorov.electroniclibrary.models.Client;
import ru.egorov.electroniclibrary.repositories.ClientRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll(Sort.by("surname"));
    }

    public Client findById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Transactional
    public void update(int id, Client client){
        client.setId(id);
        clientRepository.save(client);
    }

    @Transactional
    public void deleteById(int id){
        clientRepository.deleteById(id);
    }

}
