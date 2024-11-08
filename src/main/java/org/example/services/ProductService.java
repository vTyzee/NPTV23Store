package org.example.services;

import org.example.interfaces.AppHelper;
import org.example.interfaces.FileRepository;
import org.example.interfaces.Service;
import org.example.interfaces.Input;
import org.example.model.Product;

import java.util.List;

public class ProductService implements Service<Product> {
    private final FileRepository<Product> petStuffRepository;
    private final AppHelper<Product> appHelperPetStuff;
    private final Input inputProvider;

    public ProductService(FileRepository<Product> petStuffRepository, AppHelper<Product> appHelperPetStuff, Input inputProvider) {
        this.petStuffRepository = petStuffRepository;
        this.appHelperPetStuff = appHelperPetStuff;
        this.inputProvider = inputProvider;
    }

    @Override
    public boolean add() {
        Product product = appHelperPetStuff.create();
        if (product != null) {
            petStuffRepository.save(product);
            System.out.println("Товар успешно добавлен.");
            return true;
        }
        System.out.println("Ошибка при добавлении товара.");
        return false;
    }

    @Override
    public void print() {
        appHelperPetStuff.printList(petStuffRepository.load());
    }

    @Override
    public List<Product> list() {
        return petStuffRepository.load();
    }

    @Override
    public boolean edit(Product product) {
        List<Product> products = petStuffRepository.load();
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
            Product updatedProduct = appHelperPetStuff.create();
            if (updatedProduct != null) {
                products.set(indexToEdit, updatedProduct);
                petStuffRepository.save(products);
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
        List<Product> products = petStuffRepository.load();
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
            petStuffRepository.save(products);
            System.out.println("Товар успешно удален.");
            return true;
        } else {
            System.out.println("Некорректный выбор товара.");
        }
        return false;
    }
}
