package org.example;

import org.example.interfaces.AppHelper;
import org.example.interfaces.Input;
import org.example.interfaces.FileRepository;
import org.example.model.Product;

import java.util.List;

public class AppHelperProduct implements AppHelper<Product> {
    private final FileRepository<Product> petStuffRepository;
    private final Input inputProvider;

    public AppHelperProduct(FileRepository<Product> petStuffRepository, Input inputProvider) {
        this.petStuffRepository = petStuffRepository;
        this.inputProvider = inputProvider;
    }

    @Override
    public Product create() {
        Product product = new Product();
        System.out.print("Введите название товара: ");
        product.setName(inputProvider.getInput());
        System.out.print("Введите описание товара: ");
        product.setDescription(inputProvider.getInput());

        double price;
        while (true) {
            try {
                System.out.print("Введите цену товара: ");
                price = Double.parseDouble(inputProvider.getInput());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите корректное числовое значение для цены.");
            }
        }
        product.setPrice(price);

        System.out.print("Есть ли скидка на товар? (да/нет): ");
        String discountInput = inputProvider.getInput();
        if (discountInput.equalsIgnoreCase("да")) {
            product.setHasDiscount(true);
            double discountedPrice;
            while (true) {
                try {
                    System.out.print("Введите цену со скидкой: ");
                    discountedPrice = Double.parseDouble(inputProvider.getInput());
                    if (discountedPrice >= price) {
                        System.out.println("Цена со скидкой должна быть меньше исходной цены.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Пожалуйста, введите корректное числовое значение для цены со скидкой.");
                }
            }
            product.setDiscountedPrice(discountedPrice);
        } else {
            product.setHasDiscount(false);
        }

        return product;
    }

    @Override
    public void printList(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Список товаров пуст.");
            return;
        }
        System.out.println("------ Список товаров ------");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.printf("%d. Название: %s, Описание: %s, Цена: %.2f%s%n",
                    i + 1,
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.isHasDiscount() ? ", Цена со скидкой: " + product.getDiscountedPrice() : ""
            );
        }
    }

    @Override
    public FileRepository<Product> getRepository() {
        return petStuffRepository;
    }
}
