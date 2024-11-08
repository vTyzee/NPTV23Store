package org.example.apphelpers;

import org.example.interfaces.AppHelper;
import org.example.model.Purchase;

import java.util.List;

public class PurchaseAppHelper implements AppHelper<Purchase> {

    @Override
    public Purchase create() {
        // Реализация метода create
        return null;
    }

    @Override
    public void printList(List<Purchase> items) {
        // Реализация метода printList
    }

    @Override
    public String readInput() {
        // Реализация метода readInput
        return new java.util.Scanner(System.in).nextLine();
    }

    @Override
    public boolean handleAdd() {
        try {
            System.out.print("Введите ID продукта: ");
            String productId = readInput();
            System.out.print("Введите ID клиента: ");
            String customerId = readInput();
            System.out.print("Введите количество: ");
            int quantity = Integer.parseInt(readInput());

            Purchase purchase = new Purchase(productId, customerId, quantity);
            System.out.println("Покупка создана: " + purchase);
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void handleUpdate() {
        // Реализация метода handleUpdate
    }

    @Override
    public void handleDelete() {
        // Реализация метода handleDelete
    }

    @Override
    public void handlePrint() {
        // Реализация метода handlePrint
    }
}
