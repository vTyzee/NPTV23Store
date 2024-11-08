package org.example;

import org.example.interfaces.AppHelper;
import org.example.interfaces.Input;
import org.example.interfaces.FileRepository;
import org.example.model.Product;
import org.example.model.Purchase;
import org.example.model.Customer;

import java.util.List;

public class AppHelperPurchase implements AppHelper<Purchase> {
    private final FileRepository<Purchase> purchaseRepository;
    private final Input inputProvider;
    private final AppHelper<Customer> customerHelper;
    private final AppHelper<Product> petStuffHelper;

    public AppHelperPurchase(FileRepository<Purchase> purchaseRepository, Input inputProvider,
                             AppHelper<Customer> customerHelper, AppHelper<Product> petStuffHelper) {
        this.purchaseRepository = purchaseRepository;
        this.inputProvider = inputProvider;
        this.customerHelper = customerHelper;
        this.petStuffHelper = petStuffHelper;
    }

    @Override
    public Purchase create() {
        System.out.println("------ Создание новой покупки ------");

        Customer customer = selectCustomer();
        if (customer == null) {
            System.out.println("Некорректный выбор покупателя. Операция отменена.");
            return null;
        }

        Product product = selectPetStuff();
        if (product == null) {
            System.out.println("Некорректный выбор товара. Операция отменена.");
            return null;
        }

        boolean useDiscount = false;
        if (product.isHasDiscount()) {
            System.out.print("Товар имеет скидку. Купить по скидке? (да/нет): ");
            String discountResponse = inputProvider.getInput().trim().toLowerCase();
            useDiscount = discountResponse.equals("да");
        }

        return new Purchase(customer, product, useDiscount);
    }

    private Customer selectCustomer() {
        List<Customer> customers = customerHelper.getRepository().load();
        customerHelper.printList(customers);
        System.out.print("Выберите номер покупателя: ");
        int customerIndex;
        try {
            customerIndex = Integer.parseInt(inputProvider.getInput()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод.");
            return null;
        }

        if (customerIndex >= 0 && customerIndex < customers.size()) {
            return customers.get(customerIndex);
        }
        System.out.println("Некорректный выбор покупателя.");
        return null;
    }

    private Product selectPetStuff() {
        List<Product> products = petStuffHelper.getRepository().load();
        petStuffHelper.printList(products);
        System.out.print("Выберите номер товара: ");
        int petStuffIndex;
        try {
            petStuffIndex = Integer.parseInt(inputProvider.getInput()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод.");
            return null;
        }

        if (petStuffIndex >= 0 && petStuffIndex < products.size()) {
            return products.get(petStuffIndex);
        }
        System.out.println("Некорректный выбор товара.");
        return null;
    }

    @Override
    public void printList(List<Purchase> purchases) {
        if (purchases.isEmpty()) {
            System.out.println("Список покупок пуст.");
            return;
        }
        System.out.println("------ Список покупок ------");
        for (int i = 0; i < purchases.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, purchases.get(i));
        }
    }

    @Override
    public FileRepository<Purchase> getRepository() {
        return purchaseRepository;
    }
}
