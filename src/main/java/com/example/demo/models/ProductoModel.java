package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name="productos")
public class ProductoModel {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "id_producto")
 private Long idProducto;

 @Column(name = "sku", unique = true, nullable = false)
 private String sku;

 private String nombre;
 private String descripcion;
 private String categoria;
 private int Cantidad;

 private int stock;

 @Column(name = "precio_compra")
 private double precioCompra;

 private double precioVenta = 0.0;

 public double getPrecioVenta() {return precioVenta;}

 public void setPrecioVenta(double precioVenta) {this.precioVenta = precioVenta;}

 public Long getIdProducto() {
  return idProducto;
 }

 public void setIdProducto(Long idProducto) {
  this.idProducto = idProducto;
 }

 public String getSku() {
  return sku;
 }

 public void setSku(String sku) {
  this.sku = sku;
 }

 public String getNombre() {
  return nombre;
 }

 public void setNombre(String nombre) {
  this.nombre = nombre;
 }

 public String getDescripcion() {
  return descripcion;
 }

 public void setDescripcion(String descripcion) {
  this.descripcion = descripcion;
 }

 public String getCategoria() {
  return categoria;
 }

 public void setCategoria(String categoria) {
  this.categoria = categoria;
 }

 public int getStock() {
  return stock;
 }

 public void setStock(int stock) {
  this.stock = stock;
 }

 public double getPrecioCompra() {
  return precioCompra;
 }

 public void setPrecioCompra(double precioCompra) {
  this.precioCompra = precioCompra;
 }

 public int getCantidad() {
  return Cantidad;
 }

 public void setCantidad(int cantidad) {
  Cantidad = cantidad;
 }
}
