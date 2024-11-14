package com.example.demo.services;
import com.example.demo.models.ProductoModel;
import com.example.demo.models.WebhookPayload;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


@Service
    public class ProductoServices {

        @Autowired
        private ProductoRepository productoRepository;

        public ProductoModel guardarOActualizarProducto(ProductoModel nuevoProducto) {
            Optional<ProductoModel> productoExistenteOpt = productoRepository.findBySku(nuevoProducto.getSku());

            if (productoExistenteOpt.isPresent()) {
                ProductoModel productoExistente = productoExistenteOpt.get();
                productoExistente.setNombre(nuevoProducto.getNombre());
                productoExistente.setPrecioCompra(nuevoProducto.getPrecioCompra());
                productoExistente.setStock(nuevoProducto.getStock());
                return productoRepository.save(productoExistente);
            } else {
                return productoRepository.save(nuevoProducto);
            }
        }

        public void processWebhook(WebhookPayload payload) {
            if (!"paid".equals(payload.getStatus())) {
                throw new IllegalArgumentException("Estado de pago no válido");
            }

            for (WebhookPayload.ProductDetails product : payload.getProducts()) {
                // Buscar producto por SKU en lugar de ID
                ProductoModel existingProduct = productoRepository.findBySku(product.getSku())
                        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

                // Actualizar inventario sumando la cantidad
                existingProduct.setStock(existingProduct.getStock() + product.getQuantity());
                productoRepository.save(existingProduct);
            }
        }
    }