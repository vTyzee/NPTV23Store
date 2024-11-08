package org.example.model;

public class Customer {
    private String id;
    private String name;

    // Конструктор
    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры и сеттеры
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer [ID=" + id + ", Name=" + name + "]";
    }
}
