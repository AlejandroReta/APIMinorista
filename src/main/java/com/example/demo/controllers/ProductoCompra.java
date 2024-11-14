package com.example.demo.controllers;

public class ProductoCompra {
    private String sku;
    private int cantidad;
    private double precioUnitario;
    private String nombre; // Asegúrate de tener este atributo

    // Getters y Setters
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

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getNombre() { // Getter para nombre
        return nombre;
    }

    public void setNombre(String nombre) { // Setter para nombre
        this.nombre = nombre;
    }
}

