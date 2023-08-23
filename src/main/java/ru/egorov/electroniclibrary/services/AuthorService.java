package ru.egorov.electroniclibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorov.electroniclibrary.models.Author;
import ru.egorov.electroniclibrary.repositories.AuthorRepository;

import java.util.List;

@Service
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Author> findAll(Sort sort){
        return authorRepository.findAll(sort);
    }

    public void save(Author author){
        authorRepository.save(author);
    }

}
