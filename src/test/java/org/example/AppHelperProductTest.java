package org.example;

import org.example.interfaces.FileRepository;
import org.example.interfaces.Input;
import org.example.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AppHelperProductTest {

    @Mock
    private FileRepository<Product> petStuffRepository;

    @Mock
    private Input inputProvider;

    @InjectMocks
    private AppHelperProduct appHelperProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePetStuffWithDiscount() {
        when(inputProvider.getInput()).thenReturn("Корм для кошек", "Премиум корм", "100.0", "да", "80.0");

        Product product = appHelperProduct.create();

        assertEquals("Корм для кошек", product.getName());
        assertEquals("Премиум корм", product.getDescription());
        assertEquals(100.0, product.getPrice());
        assertTrue(product.isHasDiscount());
        assertEquals(80.0, product.getDiscountedPrice());
        verify(inputProvider, times(5)).getInput();
    }

    @Test
    void testCreatePetStuffWithoutDiscount() {
        when(inputProvider.getInput()).thenReturn("Корм для собак", "Органический корм", "120.0", "нет");

        Product product = appHelperProduct.create();

        assertEquals("Корм для собак", product.getName());
        assertEquals("Органический корм", product.getDescription());
        assertEquals(120.0, product.getPrice());
        assertTrue(!product.isHasDiscount());
        verify(inputProvider, times(4)).getInput();
    }

    @Test
    void testPrintListEmpty() {
        appHelperProduct.printList(List.of());

        verify(petStuffRepository, never()).load();
    }

    @Test
    void testPrintListWithItems() {
        Product product = new Product("Игрушка для кошек", "Мягкая игрушка", 50.0, false, 0.0);
        appHelperProduct.printList(List.of(product));

        verify(petStuffRepository, never()).load();
    }
}
