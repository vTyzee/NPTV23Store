package org.example.services;

import org.example.interfaces.AppHelper;
import org.example.interfaces.FileRepository;
import org.example.interfaces.Service;
import org.example.model.Purchase;

import java.util.List;

public class PurchaseService implements Service<Purchase> {
    private final AppHelper<Purchase> appHelper;
    private final FileRepository<Purchase> storage;

    // Конструктор, принимающий только два аргумента
    public PurchaseService(AppHelper<Purchase> appHelper, FileRepository<Purchase> storage) {
        this.appHelper = appHelper;
        this.storage = storage;
    }

    @Override
    public boolean add() {
        return appHelper.handleAdd();
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

    @Override
    public List<Purchase> getAll() {
        return storage.getAll(); // Возвращаем все покупки
    }
}
