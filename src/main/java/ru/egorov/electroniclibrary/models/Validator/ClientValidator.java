package ru.egorov.electroniclibrary.models.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.egorov.electroniclibrary.dao.ClientDAO;
import ru.egorov.electroniclibrary.models.Client;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ClientValidator implements Validator {
    private final ClientDAO clientDAO;

    @Autowired
    public ClientValidator(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Client.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;

        // check date
        if(!client.getDateOfBirth().isBefore(LocalDate.now())
                || !client.getDateOfBirth().isAfter(LocalDate.now().minusYears(120))){
            errors.rejectValue("dateOfBirth", "", "Проверьте дату рождения");
        }

        // check client in db
        if(clientDAO.checkingForFullness(client).isPresent()){
            errors.rejectValue("id", "", "Данный клиент уже зарегистрирован");
        }

    }
}
