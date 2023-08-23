package ru.egorov.electroniclibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorov.electroniclibrary.models.Client;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByNameAndSurnameAndDateOfBirth(String name, String surname, LocalDate dateOfBirth);
}
