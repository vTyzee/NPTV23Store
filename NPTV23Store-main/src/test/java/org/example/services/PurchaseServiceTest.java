package org.example.services;

import org.example.model.Customer;
import org.example.model.Product;
import org.example.model.Purchase;
import org.example.repository.CustomerRepository;
import org.example.repository.ProductRepository;
import org.example.repository.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PurchaseServiceTest {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    public void testAddPurchase() {
        // Создание клиента
        Customer customer = new Customer("Test", "Customer", "123456789");
        customerRepository.save(customer);

        // Создание продукта
        Product product = new Product("Test Product", "Test Description", 100.0, false, 0.0);
        productRepository.save(product);

        // Создание покупки
        Purchase purchase = new Purchase(customer, product, false);
        purchaseRepository.save(purchase);

        // Проверка сохранённой покупки
        List<Purchase> purchases = purchaseRepository.findAll();
        assertEquals(1, purchases.size());
        assertEquals("Test Product", purchases.get(0).getProduct().getName());
    }

    @Test
    public void testListPurchases() {
        // Проверка всех покупок через PurchaseService
        List<Purchase> purchases = purchaseService.list();
        assertNotNull(purchases);
    }
}
