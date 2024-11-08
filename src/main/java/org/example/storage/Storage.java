package org.example.storage;

import org.example.interfaces.FileRepository;
import java.util.ArrayList;
import java.util.List;

public class Storage<T> implements FileRepository<T> {
    private final List<T> items = new ArrayList<>();

    @Override
    public void save(T entity) {
        items.add(entity);
    }

    @Override
    public void save(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    @Override
    public List<T> load() {
        return new ArrayList<>(items);
    }
}
