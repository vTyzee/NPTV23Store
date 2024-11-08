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
    private FileRepository<Product> petStuffRepository;

    @Mock
    private AppHelper<Product> appHelperPetStuff;

    @Mock
    private Input inputProvider;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void add_ShouldAddPetStuffSuccessfully() {
        Product product = new Product("Toy", "Cat toy", 15.99, true, 4.5);
        when(appHelperPetStuff.create()).thenReturn(product);

        boolean result = productService.add();

        assertTrue(result);
        verify(petStuffRepository, times(1)).save(product);
    }

    @Test
    void add_ShouldReturnFalseWhenPetStuffCreationFails() {
        when(appHelperPetStuff.create()).thenReturn(null);

        boolean result = productService.add();

        assertFalse(result);
        verify(petStuffRepository, never()).save(any(Product.class));
    }

    @Test
    void print_ShouldPrintPetStuffList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Toy", "Cat toy", 15.99, true, 4.5));
        when(petStuffRepository.load()).thenReturn(products);

        productService.print();

        verify(appHelperPetStuff, times(1)).printList(products);
    }

    @Test
    void edit_ShouldEditPetStuffSuccessfully() {
        List<Product> products = new ArrayList<>();
        Product product = new Product("Toy", "Cat toy", 15.99, true, 4.5);
        products.add(product);
        when(petStuffRepository.load()).thenReturn(products);
        when(inputProvider.getInput()).thenReturn("1");
        Product updatedProduct = new Product("Updated Toy", "Updated cat toy", 19.99, false, 3.5);
        when(appHelperPetStuff.create()).thenReturn(updatedProduct);

        boolean result = productService.edit(product);

        assertTrue(result);
        verify(petStuffRepository, times(1)).save(products);
        assertEquals(updatedProduct, products.get(0));
    }

    @Test
    void edit_ShouldReturnFalseWhenIndexIsInvalid() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Toy", "Cat toy", 15.99, true, 4.5));
        when(petStuffRepository.load()).thenReturn(products);
        when(inputProvider.getInput()).thenReturn("2"); // Invalid index

        boolean result = productService.edit(products.get(0));

        assertFalse(result);
        verify(petStuffRepository, never()).save(products);
    }

    @Test
    void remove_ShouldRemovePetStuffSuccessfully() {
        List<Product> products = new ArrayList<>();
        Product product = new Product("Toy", "Cat toy", 15.99, true, 4.5);
        products.add(product);
        when(petStuffRepository.load()).thenReturn(products);
        when(inputProvider.getInput()).thenReturn("1");

        boolean result = productService.remove(product);

        assertTrue(result);
        verify(petStuffRepository, times(1)).save(products);
        assertTrue(products.isEmpty());
    }

    @Test
    void remove_ShouldReturnFalseWhenIndexIsInvalid() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Toy", "Cat toy", 15.99, true, 4.5));
        when(petStuffRepository.load()).thenReturn(products);
        when(inputProvider.getInput()).thenReturn("2"); // Invalid index

        boolean result = productService.remove(products.get(0));

        assertFalse(result);
        verify(petStuffRepository, never()).save(products);
    }
}
