package org.example.services;

import org.example.interfaces.AppHelper;
import org.example.interfaces.FileRepository;
import org.example.interfaces.Input;
import org.example.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private FileRepository<Product> productRepository;

    @Mock
    private AppHelper<Product> appHelperProduct;

    @Mock
    private Input inputProvider;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void add_ShouldAddProductSuccessfully() {
        Product product = new Product("Toy", "Cat toy", 15.99, true, 4.5);
        when(appHelperProduct.create()).thenReturn(product);

        boolean result = productService.add();

        assertTrue(result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void add_ShouldReturnFalseWhenProductCreationFails() {
        when(appHelperProduct.create()).thenReturn(null);

        boolean result = productService.add();

        assertFalse(result);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void print_ShouldPrintProductList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Toy", "Cat toy", 15.99, true, 4.5));
        when(productRepository.load()).thenReturn(products);

        productService.print();

        verify(appHelperProduct, times(1)).printList(products);
    }

    @Test
    void edit_ShouldEditProductSuccessfully() {
        List<Product> products = new ArrayList<>();
        Product originalProduct = new Product("Toy", "Cat toy", 15.99, true, 4.5);
        products.add(originalProduct);
        when(productRepository.load()).thenReturn(products);
        when(inputProvider.getInput()).thenReturn("1");
        Product updatedProduct = new Product("Updated Toy", "Updated cat toy", 19.99, false, 3.5);
        when(appHelperProduct.create()).thenReturn(updatedProduct);

        boolean result = productService.edit(originalProduct);

        assertTrue(result);
        verify(productRepository, times(1)).save(products);
        assertEquals(updatedProduct, products.get(0));
    }

    @Test
    void edit_ShouldReturnFalseWhenIndexIsInvalid() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Toy", "Cat toy", 15.99, true, 4.5));
        when(productRepository.load()).thenReturn(products);
        when(inputProvider.getInput()).thenReturn("2"); // Invalid index

        boolean result = productService.edit(products.get(0));

        assertFalse(result);
        verify(productRepository, never()).save(products);
    }

    @Test
    void remove_ShouldRemoveProductSuccessfully() {
        List<Product> products = new ArrayList<>();
        Product product = new Product("Toy", "Cat toy", 15.99, true, 4.5);
        products.add(product);
        when(productRepository.load()).thenReturn(products);
        when(inputProvider.getInput()).thenReturn("1");

        boolean result = productService.remove(product);

        assertTrue(result);
        verify(productRepository, times(1)).save(products);
        assertTrue(products.isEmpty());
    }

    @Test
    void remove_ShouldReturnFalseWhenIndexIsInvalid() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Toy", "Cat toy", 15.99, true, 4.5));
        when(productRepository.load()).thenReturn(products);
        when(inputProvider.getInput()).thenReturn("2"); // Invalid index

        boolean result = productService.remove(products.get(0));

        assertFalse(result);
        verify(productRepository, never()).save(products);
    }
}
