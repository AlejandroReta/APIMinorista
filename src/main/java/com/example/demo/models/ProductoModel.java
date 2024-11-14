package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class ProductoModel {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "id_producto")
 private Long idProducto;

 private String nombre;
 private String descripcion;
 private String categoria;
 private int stock;

 @Column(name = "precio_compra")
 private double precioCompra;

 @Column(name = "precio_venta")
 private double precioVenta;

 @Column(name = "id_proveedor")
 private Long idProveedor;

 private int cantidad;

 private String sku;

 // Getters y Setters
 public Long getIdProducto() {
  return idProducto;
 }

 public void setIdProducto(Long idProducto) {
  this.idProducto = idProducto;
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

 public double getPrecioVenta() {
  return precioVenta;
 }

 public void setPrecioVenta(double precioVenta) {
  this.precioVenta = precioVenta;
 }

 public Long getIdProveedor() {
  return idProveedor;
 }

 public void setIdProveedor(Long idProveedor) {
  this.idProveedor = idProveedor;
 }

 public int getCantidad() {
  return cantidad;
 }

 public void setCantidad(int cantidad) {
  this.cantidad = cantidad;
 }

 public String getSku() {
  return sku;
 }

 public void setSku(String sku) {
  this.sku = sku;
 }
}

