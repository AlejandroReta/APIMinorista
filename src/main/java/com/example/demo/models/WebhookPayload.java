package com.example.demo.models;
import java.util.List;

public class WebhookPayload {
    private Long orderId;
    private String status;
    private List<ProductDetails> products;
    private String timestamp;

    // Getters y Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductDetails> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDetails> products) {
        this.products = products;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // Clase interna para los detalles de los productos
    public static class ProductDetails {
        private String sku; // Identificador único del producto
        private int quantity; // Cantidad comprada
        private String name; // Nombre del producto (opcional)
        private double price; // Precio unitario del producto (opcional)

        // Getters y Setters
        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
