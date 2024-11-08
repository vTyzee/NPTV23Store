package org.example;

import org.example.apphelpers.CustomerAppHelper;
import org.example.apphelpers.ProductAppHelper;
import org.example.apphelpers.PurchaseAppHelper;
import org.example.interfaces.AppHelper;
import org.example.interfaces.FileRepository;
import org.example.interfaces.Service;
import org.example.model.Customer;
import org.example.model.Product;
import org.example.model.Purchase;
import org.example.services.CustomerService;
import org.example.services.ProductService;
import org.example.services.PurchaseService;
import org.example.storage.Storage;

public class NPTV23Store {

    public static void main(String[] args) {
        // Инициализация всех зависимостей
        AppHelper<Customer> customerAppHelper = new CustomerAppHelper();
        AppHelper<Product> productAppHelper = new ProductAppHelper();
        AppHelper<Purchase> purchaseAppHelper = new PurchaseAppHelper();

        FileRepository<Customer> customerStorage = new Storage<>();
        FileRepository<Product> productStorage = new Storage<>();
        FileRepository<Purchase> purchaseStorage = new Storage<>();

        Service<Customer> customerService = new CustomerService(customerAppHelper, customerStorage);
        Service<Product> productService = new ProductService(productAppHelper, productStorage);
        Service<Purchase> purchaseService = new PurchaseService(purchaseAppHelper, purchaseStorage);

        // Создание и запуск приложения
        App app = new App(productService, customerService, purchaseService); // Проверьте порядок аргументов
        app.run();
    }
}
