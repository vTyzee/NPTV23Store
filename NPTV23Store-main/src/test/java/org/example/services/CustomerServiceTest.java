package org.example.services;

import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer();
        customer.setName("John");
        customer.setSurname("Doe");
        customer.setPhone("123456789");
        customerRepository.save(customer);

        List<Customer> customers = customerRepository.findAll();
        assertEquals(1, customers.size());
        assertEquals("John", customers.get(0).getName());
    }

    @Test
    public void testListCustomers() {
        Customer customer1 = new Customer();
        customer1.setName("Alice");
        customer1.setSurname("Smith");
        customer1.setPhone("987654321");
        customerRepository.save(customer1);

        List<Customer> customers = customerService.list();
        assertEquals(1, customers.size());
        assertEquals("Alice", customers.get(0).getName());
    }
}
