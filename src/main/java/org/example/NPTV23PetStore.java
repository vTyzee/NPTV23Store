package org.example;

import org.example.interfaces.AppHelper;
import org.example.interfaces.FileRepository;
import org.example.interfaces.Input;
import org.example.interfaces.repository.FileStorage;
import org.example.model.Product;
import org.example.services.CustomerService;
import org.example.services.ProductService;
import org.example.services.PurchaseService;
import org.example.model.Customer;
import org.example.model.Purchase;

public class NPTV23PetStore {
    public static void main(String[] args) {
        Input inputProvider = new AppHelper.ConsoleInput();

        FileRepository<Customer> customerRepository = new FileStorage<>("Customer") {};
        FileRepository<Product> petStuffRepository = new FileStorage<>("PetStuff") {};
        FileRepository<Purchase> purchaseRepository = new FileStorage<>("Purchase") {};

        AppHelper<Customer> appHelperCustomer = new AppHelperCustomer(customerRepository, inputProvider);
        AppHelper<Product> appHelperPetStuff = new AppHelperProduct(petStuffRepository, inputProvider);
        AppHelper<Purchase> appHelperPurchase = new AppHelperPurchase(purchaseRepository, inputProvider, appHelperCustomer, appHelperPetStuff);

        CustomerService customerService = new CustomerService(customerRepository, appHelperCustomer, inputProvider);
        ProductService productService = new ProductService(petStuffRepository, appHelperPetStuff, inputProvider);
        PurchaseService purchaseService = new PurchaseService(purchaseRepository, appHelperPurchase, appHelperCustomer, appHelperPetStuff, inputProvider);

        App app = new App(customerService, productService, purchaseService, inputProvider);
        app.run();
    }
}
