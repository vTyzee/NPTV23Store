package org.example.storage;

import org.example.interfaces.FileRepository;

import java.util.ArrayList;
import java.util.List;

public class Storage<T> implements FileRepository<T> {
    private final List<T> items = new ArrayList<>();

    @Override
    public void save(T item) {
        items.add(item);
    }

    @Override
    public void delete(String id) {
        // Реализация метода удаления элемента по ID (предположим, что T имеет метод getId())
        items.removeIf(item -> item.toString().contains(id)); // Замените на более точную проверку ID, если есть метод getId()
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(items); // Возвращаем копию списка для безопасности
    }
}
