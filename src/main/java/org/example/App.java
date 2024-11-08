package org.example;

import org.example.interfaces.Input;
import org.example.interfaces.Service;
import org.example.model.Customer;
import org.example.model.Product;
import org.example.model.Purchase;

import java.util.Scanner;

public class App implements Input {

    private final Service<Product> productService;
    private final Service<Customer> customerService;
    private final Service<Purchase> purchaseService;

    public App(Service<Product> productService, Service<Customer> customerService, Service<Purchase> purchaseService) {
        this.productService = productService;
        this.customerService = customerService;
        this.purchaseService = purchaseService;
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void run() {
        System.out.println("------ Магазин NPTV23 ------");
        boolean repeat = true;
        do {
            System.out.println("Список задач:");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить продукт");
            System.out.println("2. Список продуктов");
            System.out.println("3. Редактировать продукт");
            System.out.println("4. Удалить продукт");
            System.out.println("5. Добавить клиента");
            System.out.println("6. Список клиентов");
            System.out.println("7. Редактировать клиента");
            System.out.println("8. Удалить клиента");
            System.out.println("9. Купить товар");
            System.out.println("10. Список приобретенных товаров");

            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(getInput());
            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    if (productService.add()) {
                        System.out.println("Продукт добавлен.");
                    } else {
                        System.out.println("Не удалось добавить продукт.");
                    }
                    break;
                case 2:
                    productService.print();
                    break;
                case 3:
                    productService.update();
                    break;
                case 4:
                    productService.delete();
                    break;
                case 5:
                    if (customerService.add()) {
                        System.out.println("Клиент добавлен.");
                    } else {
                        System.out.println("Не удалось добавить клиента.");
                    }
                    break;
                case 6:
                    customerService.print();
                    break;
                case 7:
                    customerService.update();
                    break;
                case 8:
                    customerService.delete();
                    break;
                case 9:
                    if (purchaseService.add()) {
                        System.out.println("Покупка завершена.");
                    } else {
                        System.out.println("Не удалось совершить покупку.");
                    }
                    break;
                case 10:
                    purchaseService.print();
                    break;
                default:
                    System.out.println("Выберите задачу из списка!");
            }
        } while (repeat);
        System.out.println("До свидания :)");
    }
}
