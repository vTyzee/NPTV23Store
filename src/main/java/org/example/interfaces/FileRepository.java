package org.example.interfaces;

import java.util.List;

public interface FileRepository<T> {
    void save(T item);
    void delete(String id);
    List<T> getAll(); // Метод для получения всех элементов
}
