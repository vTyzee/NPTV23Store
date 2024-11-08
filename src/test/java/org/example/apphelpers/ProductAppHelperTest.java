package org.example.apphelpers;

import org.example.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class ProductAppHelperTest {

    private ProductAppHelper productAppHelper;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        productAppHelper = new ProductAppHelper();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCreateProduct() {
        ProductAppHelper spyHelper = spy(productAppHelper);

        // Мокируем ввод пользователя
        doReturn("101", "Sample Product", "29.99").when(spyHelper).readInput();
        Product product = spyHelper.create();

        assertNotNull(product);
        assertEquals("101", product.getId());
        assertEquals("Sample Product", product.getName());
        assertEquals(29.99, product.getPrice(), 0.01);
        assertTrue(outContent.toString().contains("Введите ID продукта:"));
    }

    @Test
    public void testCreateProductHandlesError() {
        ProductAppHelper spyHelper = spy(productAppHelper);

        // Мокируем ввод с ошибкой
        doReturn("101", "Sample Product", "invalid").when(spyHelper).readInput();
        Product product = spyHelper.create();

        assertNull(product);
        assertTrue(outContent.toString().contains("Ошибка"));
    }

    @Test
    public void testPrintProducts() {
        List<Product> products = List.of(
                new Product("101", "Product1", 20.0),
                new Product("102", "Product2", 30.0)
        );

        productAppHelper.printList(products);

        assertTrue(outContent.toString().contains("1. 101 - Product1 - 20.0"));
        assertTrue(outContent.toString().contains("2. 102 - Product2 - 30.0"));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }
}
