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
                // Buscar producto por SKU
                Optional<ProductoModel> productoExistenteOpt = productoRepository.findBySku(product.getSku());

                if (productoExistenteOpt.isPresent()) {
                    ProductoModel productoExistente = productoExistenteOpt.get();
                    productoExistente.setStock(productoExistente.getStock() + product.getQuantity());
                    productoRepository.save(productoExistente);
                } else {
                    //Si el producto no existe, crea uno nuevo con información básica
                    ProductoModel nuevoProducto = new ProductoModel();
                    nuevoProducto.setSku(product.getSku());
                    nuevoProducto.setNombre("Nombre por definir");
                    nuevoProducto.setDescripcion("Descripción por definir");
                    nuevoProducto.setCategoria("Categoría por definir");
                    nuevoProducto.setStock(product.getQuantity());
                    nuevoProducto.setPrecioVenta(0.0);  // Establece un valor predeterminado para precioVenta
                    productoRepository.save(nuevoProducto);
                }
            }
        }
    }