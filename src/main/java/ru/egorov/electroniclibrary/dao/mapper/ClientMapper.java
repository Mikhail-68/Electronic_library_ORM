package ru.egorov.electroniclibrary.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.egorov.electroniclibrary.models.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        client.setId(rs.getInt("client_id"));
        client.setName(rs.getString("name"));
        client.setSurname(rs.getString("surname"));
        client.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        client.setPhone(rs.getString("phone"));
        return client;
    }
}
