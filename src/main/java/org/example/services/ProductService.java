package org.example.services;

import org.example.interfaces.AppHelper;
import org.example.interfaces.FileRepository;
import org.example.interfaces.Service;
import org.example.model.Product;

import java.util.List;

public class ProductService implements Service<Product> {
    private final AppHelper<Product> appHelper;
    private final FileRepository<Product> storage;

    public ProductService(AppHelper<Product> appHelper, FileRepository<Product> storage) {
        this.appHelper = appHelper;
        this.storage = storage;
    }

    @Override
    public boolean add() {
        Product product = appHelper.create();
        if (product != null) {
            storage.save(product);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAll() {
        return storage.getAll(); // Метод для получения всех продуктов
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
