package org.example.apphelpers;

import org.example.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

public class CustomerAppHelperTest {

    private CustomerAppHelper customerAppHelper;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        customerAppHelper = new CustomerAppHelper();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCreate() {
        // Мокируем ввод пользователя
        CustomerAppHelper spyHelper = Mockito.spy(customerAppHelper);
        doReturn("123", "John Doe").when(spyHelper).readInput();

        Customer customer = spyHelper.create();

        assertNotNull(customer);
        assertEquals("123", customer.getId());
        assertEquals("John Doe", customer.getName());
    }

    @Test
    public void testPrintList() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("1", "Alice"));
        customers.add(new Customer("2", "Bob"));

        customerAppHelper.printList(customers);

        String expectedOutput1 = "1. 1 - Alice";
        String expectedOutput2 = "2. 2 - Bob";

        assertTrue(outContent.toString().contains(expectedOutput1));
        assertTrue(outContent.toString().contains(expectedOutput2));
    }

    @Test
    public void testCreateHandlesError() {
        // Мокируем, чтобы readInput выбрасывал исключение
        CustomerAppHelper spyHelper = Mockito.spy(customerAppHelper);
        doThrow(new RuntimeException("Test exception")).when(spyHelper).readInput();

        Customer customer = spyHelper.create();

        assertNull(customer);
        assertTrue(outContent.toString().contains("Ошибка ввода данных: Test exception"));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }
}
