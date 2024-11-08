package org.example.services;

import org.example.apphelpers.ProductAppHelper;
import org.example.model.Product;
import org.example.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    private ProductService productService;
    private ProductAppHelper appHelperMock;
    private Storage<Product> storage;

    @BeforeEach
    public void setUp() {
        appHelperMock = Mockito.spy(new ProductAppHelper());
        storage = new Storage<>();

        // Мокируем метод create, чтобы он возвращал объект Product
        doReturn(new Product("001", "Sample Product", 29.99)).when(appHelperMock).create();

        productService = new ProductService(appHelperMock, storage);
    }

    @Test
    public void testAddProduct() {
        boolean result = productService.add(); // Добавление продукта через метод add()

        assertTrue(result, "Продукт должен быть успешно добавлен");
        List<Product> products = productService.getAll(); // Получаем все продукты
        assertEquals(1, products.size(), "Должен быть один продукт в хранилище");
        assertEquals("001", products.get(0).getId());
        assertEquals("Sample Product", products.get(0).getName());
        assertEquals(29.99, products.get(0).getPrice());
    }

    @Test
    public void testGetAllProducts() {
        // Добавляем два продукта через замокированный метод create()
        productService.add();
        doReturn(new Product("002", "Another Product", 49.99)).when(appHelperMock).create();
        productService.add();

        List<Product> products = productService.getAll();
        assertEquals(2, products.size(), "Должно быть два продукта в хранилище");
        assertEquals("001", products.get(0).getId());
        assertEquals("002", products.get(1).getId());
    }
}
