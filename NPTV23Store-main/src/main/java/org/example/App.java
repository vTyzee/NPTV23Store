package org.example;

import org.example.services.CustomerService;
import org.example.services.ProductService;
import org.example.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class App {

    private final CustomerService customerService;
    private final ProductService productService;
    private final PurchaseService purchaseService;

    @Autowired
    public App(CustomerService customerService, ProductService productService, PurchaseService purchaseService) {
        this.customerService = customerService;
        this.productService = productService;
        this.purchaseService = purchaseService;
    }

    public void run() {
        boolean repeat = true;
        do {
            System.out.println("\nСписок задач:");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить клиента");
            System.out.println("2. Посмотреть список клиентов");
            System.out.println("3. Добавить товар");
            System.out.println("4. Посмотреть список товаров");
            System.out.println("5. Совершить покупку");
            System.out.println("6. Посмотреть историю покупок");
            System.out.print("Выберите номер задачи: ");

            try {
                int task = Integer.parseInt(new java.util.Scanner(System.in).nextLine());
                switch (task) {
                    case 0:
                        repeat = false;
                        break;
                    case 1:
                        if (customerService.add()) {
                            System.out.println("Клиент успешно добавлен.");
                        } else {
                            System.out.println("Не удалось добавить клиента.");
                        }
                        break;
                    case 2:
                        customerService.print();
                        break;
                    case 3:
                        if (productService.add()) {
                            System.out.println("Товар успешно добавлен.");
                        } else {
                            System.out.println("Не удалось добавить товар.");
                        }
                        break;
                    case 4:
                        productService.print();
                        break;
                    case 5:
                        if (purchaseService.add()) {
                            System.out.println("Покупка успешно совершена.");
                        } else {
                            System.out.println("Не удалось совершить покупку.");
                        }
                        break;
                    case 6:
                        purchaseService.print();
                        break;
                    default:
                        System.out.println("Такого номера задачи в списке нет!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода. Введите число.");
            }
        } while (repeat);
        System.out.println("До свидания!");
    }
}
