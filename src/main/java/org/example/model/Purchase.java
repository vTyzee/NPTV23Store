package org.example.model;

import java.io.Serializable;
import java.util.UUID;
import java.time.LocalDate;

public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private Customer customer;
    private Product product;
    private LocalDate purchaseDate;
    private boolean useDiscount;

    public Purchase(Customer customer, Product product, boolean useDiscount) {
        this.id = UUID.randomUUID();
        this.customer = customer;
        this.product = product;
        this.purchaseDate = LocalDate.now();
        this.useDiscount = useDiscount;
    }

    // Геттеры и сеттеры

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getPetStuff() {
        return product;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public boolean isUseDiscount() {
        return useDiscount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Покупатель: ").append(customer);
        sb.append(", Товар: ").append(product);

        if (useDiscount) {
            sb.append(" (приобретен по скидке за: ").append(product.getDiscountedPrice()).append(")");
        } else {
            sb.append(" (приобретен по полной цене: ").append(product.getPrice()).append(")");
        }

        sb.append(", Дата покупки: ").append(purchaseDate);
        return sb.toString();
    }
}
