package ru.egorov.electroniclibrary.models.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.egorov.electroniclibrary.dao.AuthorDAO;
import ru.egorov.electroniclibrary.models.Author;

@Component
public class AuthorValidator implements Validator {

    private final AuthorDAO authorDAO;

    @Autowired
    public AuthorValidator(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Author.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Author author = (Author) target;
        if(authorDAO.get(author.getName()).isPresent()){
            errors.rejectValue("name", "", "Данный автор уже существует");
        }
    }
}
