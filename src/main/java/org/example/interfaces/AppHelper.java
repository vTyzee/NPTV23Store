package org.example.interfaces;

import java.util.List;

public interface AppHelper<T> {
    T create();
    void printList(List<T> items);
    String readInput();
    boolean handleAdd();
    void handleUpdate();
    void handleDelete();
    void handlePrint();
}
