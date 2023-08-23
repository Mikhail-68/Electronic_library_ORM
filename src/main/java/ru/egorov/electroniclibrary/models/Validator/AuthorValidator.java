package ru.egorov.electroniclibrary.models.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.egorov.electroniclibrary.models.Author;
import ru.egorov.electroniclibrary.repositories.AuthorRepository;

@Component
public class AuthorValidator implements Validator {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorValidator(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Author.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Author author = (Author) target;
        if(authorRepository.findByName(author.getName()).isPresent()){
            errors.rejectValue("name", "", "Данный автор уже существует");
        }
    }
}
