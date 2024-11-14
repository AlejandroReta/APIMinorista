package com.example.demo.repositories;


import com.example.demo.models.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoModel, Long> {
    ProductoModel findBySku(String sku);
}
