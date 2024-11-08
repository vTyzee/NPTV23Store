package org.example.model;

public class Purchase {
    private String productId;
    private String customerId;
    private int quantity;

    public Purchase(String productId, String customerId, int quantity) {
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Purchase{productId='" + productId + "', customerId='" + customerId + "', quantity=" + quantity + "}";
    }
}
