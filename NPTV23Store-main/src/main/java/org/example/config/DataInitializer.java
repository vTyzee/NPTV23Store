package org.example.config;

import org.example.model.Customer;
import org.example.model.Product;
import org.example.repository.CustomerRepository;
import org.example.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(CustomerRepository customerRepository, ProductRepository productRepository) {
        return args -> {
            // Добавление тестовых клиентов
            customerRepository.save(new Customer("John", "Doe", "123456789"));
            customerRepository.save(new Customer("Jane", "Smith", "987654321"));

            // Добавление тестовых товаров
            productRepository.save(new Product("Laptop", "Gaming Laptop", 1200.50, false, 0));
            productRepository.save(new Product("Phone", "Smartphone", 800.00, true, 750.00));
        };
    }
}
