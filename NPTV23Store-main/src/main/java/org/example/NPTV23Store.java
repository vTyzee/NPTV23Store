package org.example;

import org.example.helpers.AppHelperCustomer;
import org.example.helpers.AppHelperProduct;
import org.example.helpers.AppHelperPurchase;
import org.example.interfaces.AppHelper;
import org.example.interfaces.FileRepository;
import org.example.interfaces.Input;
import org.example.repository.FileStorage;
import org.example.services.CustomerService;
import org.example.services.ProductService;
import org.example.services.PurchaseService;
import org.example.model.Customer;
import org.example.model.Product;
import org.example.model.Purchase;

public class NPTV23Store {
    public static void main(String[] args) {
        Input inputProvider = new AppHelper.ConsoleInput();

        FileRepository<Customer> customerRepository = new FileStorage<>("Customer") {};
        FileRepository<Product> productRepository = new FileStorage<>("Products") {};
        FileRepository<Purchase> purchaseRepository = new FileStorage<>("Purchase") {};

        AppHelper<Customer> appHelperCustomer = new AppHelperCustomer(customerRepository, inputProvider);
        AppHelper<Product> appHelperProduct = new AppHelperProduct(productRepository, inputProvider);
        AppHelper<Purchase> appHelperPurchase = new AppHelperPurchase(purchaseRepository, inputProvider, appHelperCustomer, appHelperProduct);

        CustomerService customerService = new CustomerService(customerRepository, appHelperCustomer, inputProvider);
        ProductService productService = new ProductService(productRepository, appHelperProduct, inputProvider);
        PurchaseService purchaseService = new PurchaseService(purchaseRepository, appHelperPurchase, appHelperCustomer, appHelperProduct, inputProvider);

        App app = new App(customerService, productService, purchaseService, inputProvider);
        app.run();
    }
}
