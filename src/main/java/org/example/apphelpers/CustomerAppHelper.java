package org.example.apphelpers;

import org.example.interfaces.AppHelper;
import org.example.model.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerAppHelper implements AppHelper<Customer> {
    private final Scanner scanner = new Scanner(System.in);

    public String readInput() {
        return scanner.nextLine();
    }

    @Override
    public Customer create() {
        try {
            System.out.print("Введите ID клиента: ");
            String id = readInput();
            System.out.print("Введите имя клиента: ");
            String name = readInput();
            return new Customer(id, name);
        } catch (Exception e) {
            System.out.println("Ошибка ввода данных: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void printList(List<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("Список клиентов пуст.");
        } else {
            int index = 1;
            for (Customer customer : customers) {
                System.out.println(index + ". " + customer.getId() + " - " + customer.getName());
                index++;
            }
        }
    }

    @Override
    public boolean handleAdd() {
        Customer customer = create();
        if (customer != null) {
            System.out.println("Клиент добавлен: " + customer);
            return true;
        }
        return false;
    }

    @Override
    public void handleUpdate() {
        System.out.print("Введите ID клиента для обновления: ");
        String id = readInput();
        System.out.print("Введите новое имя клиента: ");
        String newName = readInput();
        // Реализуйте логику обновления клиента через вызов методов сервиса
    }

    @Override
    public void handleDelete() {
        System.out.print("Введите ID клиента для удаления: ");
        String id = readInput();
        // Реализуйте логику удаления клиента через вызов методов сервиса
    }

    @Override
    public void handlePrint() {
        // Предположим, что список клиентов будет передан в метод или доступен через сервис
        List<Customer> customers = getCustomersFromService(); // Замените вызовом вашего сервиса

        if (customers != null && !customers.isEmpty()) {
            printList(customers);
        } else {
            System.out.println("Список клиентов пуст.");
        }
    }

    // Метод-заглушка, замените его вызовом вашего сервиса
    private List<Customer> getCustomersFromService() {
        // Верните список клиентов из сервиса или другого источника
        return List.of(); // Пример возвращения пустого списка
    }
}