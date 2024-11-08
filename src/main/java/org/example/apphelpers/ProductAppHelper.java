package org.example.apphelpers;

import org.example.interfaces.AppHelper;
import org.example.model.Product;

import java.util.List;
import java.util.Scanner;

public class ProductAppHelper implements AppHelper<Product> {
    private final Scanner scanner = new Scanner(System.in);

    public String readInput() {
        return scanner.nextLine();
    }

    @Override
    public Product create() {
        try {
            System.out.print("Введите ID продукта: ");
            String id = readInput();
            System.out.print("Введите название продукта: ");
            String name = readInput();
            System.out.print("Введите цену продукта: ");
            double price = Double.parseDouble(readInput());
            return new Product(id, name, price);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: цена должна быть числом.");
            return null;
        } catch (Exception e) {
            System.out.println("Ошибка ввода данных: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void printList(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Список продуктов пуст.");
        } else {
            int index = 1;
            for (Product product : products) {
                System.out.println(index + ". " + product.getId() + " - " + product.getName() + " - " + product.getPrice() + " руб.");
                index++;
            }
        }
    }

    @Override
    public boolean handleAdd() {
        Product product = create();
        if (product != null) {
            System.out.println("Продукт добавлен: " + product);
            return true;
        }
        return false;
    }

    @Override
    public void handleUpdate() {
        System.out.print("Введите ID продукта для обновления: ");
        String id = readInput();
        System.out.print("Введите новое название продукта: ");
        String newName = readInput();
        System.out.print("Введите новую цену продукта: ");
        try {
            double newPrice = Double.parseDouble(readInput());
            // Реализуйте логику обновления продукта через вызов методов сервиса
            System.out.println("Продукт обновлен: ID=" + id + ", новое название=" + newName + ", новая цена=" + newPrice);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: цена должна быть числом.");
        }
    }

    @Override
    public void handleDelete() {
        System.out.print("Введите ID продукта для удаления: ");
        String id = readInput();
        // Реализуйте логику удаления продукта через вызов методов сервиса
        System.out.println("Продукт с ID=" + id + " удален.");
    }

    @Override
    public void handlePrint() {
        // Вызовите метод для получения списка продуктов из сервиса и передайте его в printList
        List<Product> products = getProductsFromService(); // Замените на реальный вызов вашего сервиса

        if (products != null && !products.isEmpty()) {
            printList(products);
        } else {
            System.out.println("Список продуктов пуст.");
        }
    }

    // Метод-заглушка, замените его на вызов вашего реального сервиса
    private List<Product> getProductsFromService() {
        // Верните список продуктов из сервиса или другого источника
        return List.of(); // Пример возвращения пустого списка
    }
}
