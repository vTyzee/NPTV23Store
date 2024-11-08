package org.example.apphelpers;

import org.example.model.Purchase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PurchaseAppHelperTest {
    private PurchaseAppHelper purchaseAppHelper;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        purchaseAppHelper = new PurchaseAppHelper();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testHandleAdd() {
        PurchaseAppHelper spyHelper = Mockito.spy(purchaseAppHelper);

        // Мокируем ввод пользователя
        doReturn("001", "002", "5").when(spyHelper).readInput();

        boolean result = spyHelper.handleAdd();

        // Проверяем результат выполнения и ожидаемое поведение
        assertTrue(result, "Ожидалось, что handleAdd вернет true, но он вернул false");
        assertTrue(outContent.toString().contains("Введите ID продукта:"));
        assertTrue(outContent.toString().contains("Введите ID клиента:"));
        assertTrue(outContent.toString().contains("Введите количество:"));
        assertTrue(outContent.toString().contains("Покупка создана:"));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }
}
