package org.example.services;

import org.example.apphelpers.CustomerAppHelper;
import org.example.model.Customer;
import org.example.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

public class CustomerServiceTest {
    private CustomerService customerService;
    private CustomerAppHelper appHelperMock;
    private Storage<Customer> storage;

    @BeforeEach
    public void setUp() {
        appHelperMock = Mockito.spy(new CustomerAppHelper());
        storage = new Storage<>();

        // Мокируем метод create, чтобы он возвращал объект Customer
        doReturn(new Customer("123", "John Doe")).when(appHelperMock).create();

        customerService = new CustomerService(appHelperMock, storage);
    }

    @Test
    public void testAddCustomer() {
        boolean result = customerService.add(); // Добавление клиента через метод add()

        assertTrue(result, "Клиент должен быть успешно добавлен");
        List<Customer> customers = customerService.getAll(); // Получаем всех клиентов
        assertEquals(1, customers.size(), "Должен быть один клиент в хранилище");
        assertEquals("123", customers.get(0).getId());
        assertEquals("John Doe", customers.get(0).getName());
    }

    @Test
    public void testGetAllCustomers() {
        // Добавляем двух клиентов через замокированный метод create()
        customerService.add();
        doReturn(new Customer("124", "Jane Doe")).when(appHelperMock).create();
        customerService.add();

        List<Customer> customers = customerService.getAll();
        assertEquals(2, customers.size(), "Должно быть два клиента в хранилище");
        assertEquals("123", customers.get(0).getId());
        assertEquals("124", customers.get(1).getId());
    }
}
