package org.example.services;

import org.example.interfaces.AppHelper;
import org.example.interfaces.FileRepository;
import org.example.interfaces.Service;
import org.example.interfaces.Input;
import org.example.model.Product;

import java.util.List;

public class ProductService implements Service<Product> {
    private final FileRepository<Product> productRepository;
    private final AppHelper<Product> appHelperProduct;
    private final Input inputProvider;

    public ProductService(FileRepository<Product> productRepository, AppHelper<Product> appHelperProduct, Input inputProvider) {
        this.productRepository = productRepository;
        this.appHelperProduct = appHelperProduct;
        this.inputProvider = inputProvider;
    }

    @Override
    public boolean add() {
        Product product = appHelperProduct.create();
        if (product != null) {
            productRepository.save(product);
            System.out.println("Товар успешно добавлен.");
            return true;
        }
        System.out.println("Ошибка при добавлении товара.");
        return false;
    }

    @Override
    public void print() {
        appHelperProduct.printList(productRepository.load());
    }

    @Override
    public List<Product> list() {
        return productRepository.load();
    }

    @Override
    public boolean edit(Product product) {
        List<Product> products = productRepository.load();
        if (products.isEmpty()) {
            System.out.println("Список товаров пуст. Нечего редактировать.");
            return false;
        }

        print();
        System.out.print("Введите номер товара для редактирования: ");
        int indexToEdit;
        try {
            indexToEdit = Integer.parseInt(inputProvider.getInput()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод.");
            return false;
        }

        if (indexToEdit >= 0 && indexToEdit < products.size()) {
            Product updatedProduct = appHelperProduct.create();
            if (updatedProduct != null) {
                products.set(indexToEdit, updatedProduct);
                productRepository.save(products);
                System.out.println("Товар успешно отредактирован.");
                return true;
            }
        } else {
            System.out.println("Некорректный выбор товара.");
        }
        return false;
    }

    @Override
    public boolean remove(Product product) {
        List<Product> products = productRepository.load();
        if (products.isEmpty()) {
            System.out.println("Список товаров пуст. Нечего удалять.");
            return false;
        }

        print();
        System.out.print("Введите номер товара для удаления: ");
        int indexToRemove;
        try {
            indexToRemove = Integer.parseInt(inputProvider.getInput()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод.");
            return false;
        }

        if (indexToRemove >= 0 && indexToRemove < products.size()) {
            products.remove(indexToRemove);
            productRepository.save(products);
            System.out.println("Товар успешно удален.");
            return true;
        } else {
            System.out.println("Некорректный выбор товара.");
        }
        return false;
    }
}
