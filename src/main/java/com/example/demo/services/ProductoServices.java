package com.example.demo.services;
import com.example.demo.controllers.ProductoCompra;
import com.example.demo.models.ProductoModel;
import com.example.demo.models.WebhookPayload;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@Service
    public class ProductoServices {

        @Autowired
        private ProductoRepository productoRepository;

    public void comprarProductos(List<ProductoCompra> productosComprados) {
        for (ProductoCompra productoCompra : productosComprados) {
            Optional<ProductoModel> productoExistenteOpt = productoRepository.findBySku(productoCompra.getSku());

            if (productoExistenteOpt.isPresent()) {
                ProductoModel productoExistente = productoExistenteOpt.get();
                productoExistente.setStock(productoExistente.getStock() + productoCompra.getCantidad());
                productoRepository.save(productoExistente);
            } else {
                ProductoModel nuevoProducto = new ProductoModel();
                nuevoProducto.setSku(productoCompra.getSku());
                nuevoProducto.setNombre(productoCompra.getNombre());
                nuevoProducto.setDescripcion("Descripción por definir");
                nuevoProducto.setCategoria("Categoría por definir");
                nuevoProducto.setStock(productoCompra.getCantidad());
                nuevoProducto.setPrecioCompra(productoCompra.getPrecioUnitario());
                nuevoProducto.setPrecioVenta(0.0); // Asignar precio de venta por defecto o ajustarlo
                nuevoProducto.setIdProveedor(1L); // Establecer un proveedor predeterminado si es necesario
                nuevoProducto.setCantidad(productoCompra.getCantidad());

                productoRepository.save(nuevoProducto);
            }
        }
    }
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