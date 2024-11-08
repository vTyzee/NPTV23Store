package org.example.services;

import org.example.apphelpers.PurchaseAppHelper;
import org.example.model.Purchase;
import org.example.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseServiceTest {
    private PurchaseService purchaseService;
    private Storage<Purchase> storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage<>();
        purchaseService = new PurchaseService(new PurchaseAppHelper(), storage);
    }

    @Test
    public void testAddPurchase() {
        Purchase purchase = new Purchase("P001", "C001", 5);
        storage.save(purchase);

        assertNotNull(storage.getAll());
        assertEquals(1, storage.getAll().size());
    }
}
