package org.example.interfaces;

import java.util.List;

public interface Service<T> {
    boolean add();
    List<T> getAll(); // Метод для получения всех элементов
    void print();
    void update();
    void delete();
}
