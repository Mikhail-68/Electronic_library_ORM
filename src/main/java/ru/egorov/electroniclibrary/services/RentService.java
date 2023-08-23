package ru.egorov.electroniclibrary.services;

import org.springframework.stereotype.Service;
import ru.egorov.electroniclibrary.dao.RentDAO;
import ru.egorov.electroniclibrary.models.Client;
import ru.egorov.electroniclibrary.models.Rent;

import java.util.List;

@Service
public class RentService {
    private final RentDAO rentDAO;

    public RentService(RentDAO rentDAO) {
        this.rentDAO = rentDAO;
    }

    public void add(int clientId, int bookId){
        rentDAO.add(clientId, bookId);
    }

    public void confirmReturnBook(int rentId){
        rentDAO.confirmReturnBook(rentId);
    }

    public List<Rent> getByClient(int clientId){
        return rentDAO.getByClient(clientId);
    }

    public List<Rent> getByBook(int bookId){
        return rentDAO.getByBook(bookId);
    }
}
