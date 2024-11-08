package org.example;

import org.example.Helpers.AppHelperCustomer;
import org.example.Helpers.AppHelperProduct;
import org.example.Helpers.AppHelperPurchase;
import org.example.interfaces.AppHelper;
import org.example.interfaces.Input;
import org.example.storage.Storage;
import org.example.services.CustomerService;
import org.example.services.ProductService;
import org.example.services.PurchaseService;
import org.example.model.Customer;
import org.example.model.Product;
import org.example.model.Purchase;

public class NPTV23Store {
    public static void main(String[] args) {
        Input inputProvider = new AppHelper.ConsoleInput();

        // Initialize repositories without creating Lists in main
        Storage<Customer> customerRepository = new Storage<>();
        Storage<Product> productRepository = new Storage<>();
        Storage<Purchase> purchaseRepository = new Storage<>();

        // Set up application helpers with repositories
        AppHelper<Customer> appHelperCustomer = new AppHelperCustomer(customerRepository, inputProvider);
        AppHelper<Product> appHelperProduct = new AppHelperProduct(productRepository, inputProvider);
        AppHelper<Purchase> appHelperPurchase = new AppHelperPurchase(purchaseRepository, inputProvider, appHelperCustomer, appHelperProduct);

        // Initialize services with the appropriate helpers and input provider
        CustomerService customerService = new CustomerService(appHelperCustomer, inputProvider);
        ProductService productService = new ProductService(appHelperProduct, inputProvider);
        PurchaseService purchaseService = new PurchaseService(appHelperPurchase, appHelperCustomer, appHelperProduct, inputProvider);

        // Start the application
        App app = new App(customerService, productService, purchaseService, inputProvider);
        app.run();
    }
}
