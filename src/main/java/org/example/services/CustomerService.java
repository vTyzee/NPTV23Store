package org.example.services;

import org.example.interfaces.AppHelper;
import org.example.interfaces.FileRepository;
import org.example.interfaces.Service;
import org.example.model.Customer;

import java.util.List;

public class CustomerService implements Service<Customer> {

    private final AppHelper<Customer> appHelper;
    private final FileRepository<Customer> storage;

    // Конструктор с параметрами
    public CustomerService(AppHelper<Customer> appHelper, FileRepository<Customer> storage) {
        this.appHelper = appHelper;
        this.storage = storage;
    }

    @Override
    public boolean add() {
        Customer customer = appHelper.create();
        if (customer != null) {
            storage.save(customer);
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> getAll() {
        return storage.getAll(); // Метод для получения всех клиентов
    }

    @Override
    public void print() {
        appHelper.handlePrint();
    }

    @Override
    public void update() {
        appHelper.handleUpdate();
    }

    @Override
    public void delete() {
        appHelper.handleDelete();
    }
}
