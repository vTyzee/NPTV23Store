package org.example.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    private LocalDate purchaseDate;
    private boolean useDiscount;

    // Конструкторы
    public Purchase() {}

    public Purchase(Customer customer, Product product, boolean useDiscount) {
        this.customer = customer;
        this.product = product;
        this.purchaseDate = LocalDate.now();
        this.useDiscount = useDiscount;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isUseDiscount() {
        return useDiscount;
    }

    public void setUseDiscount(boolean useDiscount) {
        this.useDiscount = useDiscount;
    }
}
