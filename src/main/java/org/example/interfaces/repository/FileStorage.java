package org.example.interfaces.repository;

import org.example.interfaces.FileRepository;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage<T> implements FileRepository<T> {
    private String filename;

    public FileStorage(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(T entity) {
        List<T> items = load();
        items.add(entity);
        save(items);
    }

    @Override
    public void save(List<T> items) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(items);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    @Override
    public List<T> load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
