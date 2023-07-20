package ru.egorov.electroniclibrary.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.egorov.electroniclibrary.dao.mapper.ClientMapper;
import ru.egorov.electroniclibrary.models.Client;

import java.util.List;

@Component
public class ClientDAO {

    private final JdbcTemplate jdbcTemplate;

    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Client> getAll() {
        return jdbcTemplate.query("SELECT * FROM client ORDER BY surname, name", new ClientMapper());
    }

    public Client get(int id) {
        return jdbcTemplate.query("SELECT * FROM client WHERE client_id=?", new Object[]{id}, new ClientMapper())
                .stream().findAny().orElse(null);
    }

    public void add(Client client) {
        jdbcTemplate.update("INSERT INTO client (name, surname, date_of_birth, phone) VALUES (?, ?, ?, ?)",
                client.getName(), client.getSurname(), client.getDateOfBirth(), client.getPhone());
    }

    public void update(Client client) {
        jdbcTemplate.update("UPDATE client SET name=?, surname=?, date_of_birth=?, phone=? WHERE client_id=?",
                client.getName(), client.getSurname(), client.getDateOfBirth(), client.getPhone(), client.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM client WHERE client_id=?", id);
    }

}
