package com.example.demo.controllers;

public class ProductoCompra {
    private String sku;   // El código del producto
    private int cantidad;// Cuántos quieres comprar
    private double precioUnitario;
    // Getters y setters

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
