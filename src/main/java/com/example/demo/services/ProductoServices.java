package com.example.demo.services;
import com.example.demo.models.ProductoModel;
import com.example.demo.models.WebhookPayload;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



    @Service
    public class ProductoServices {

        @Autowired
        private ProductoRepository productoRepository;

        public ProductoModel guardarOActualizarProducto(ProductoModel nuevoProducto) {
            ProductoModel productoExistente = productoRepository.findBySku(nuevoProducto.getSku());

            if (productoExistente != null) {
                // Actualiza el producto existente
                productoExistente.setNombre(nuevoProducto.getNombre());
                productoExistente.setPrecioCompra(nuevoProducto.getPrecioCompra());
                productoExistente.setCantidad(nuevoProducto.getCantidad());
                return productoRepository.save(productoExistente);
            } else {
                // Guarda el nuevo producto
                return productoRepository.save(nuevoProducto);
            }
        }
        public void processWebhook(WebhookPayload payload) {
            if (!"paid".equals(payload.getStatus())) {
                throw new IllegalArgumentException("Estado de pago no válido");
            }

            for (WebhookPayload.ProductDetails product : payload.getProducts()) {
                ProductoModel existingProduct = productoRepository.findById(product.getProductId())
                        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

                // Actualiza el inventario (suma la cantidad)
                existingProduct.setStock(existingProduct.getStock() + product.getQuantity());
                productoRepository.save(existingProduct);
            }
        }
}