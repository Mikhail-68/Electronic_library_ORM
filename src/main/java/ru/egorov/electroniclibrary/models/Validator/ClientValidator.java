package ru.egorov.electroniclibrary.models.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.egorov.electroniclibrary.models.Client;
import ru.egorov.electroniclibrary.repositories.ClientRepository;

import java.time.LocalDate;

@Component
public class ClientValidator implements Validator {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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
        if(clientRepository.findByNameAndSurnameAndDateOfBirth(client.getName(), client.getSurname(), client.getDateOfBirth()).size() > 0){
            errors.rejectValue("id", "", "Данный клиент уже зарегистрирован");
        }
    }
}
