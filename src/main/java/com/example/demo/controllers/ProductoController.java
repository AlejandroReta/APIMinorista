package com.example.demo.controllers;

import com.example.demo.models.ProductoModel;
import com.example.demo.models.WebhookPayload;
import com.example.demo.services.ProductoServices;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoServices productoService;

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping("/comprar")
    public ResponseEntity<?> comprarProductos(@RequestBody List<ProductoCompra> productosComprados) {
        for (ProductoCompra productoCompra : productosComprados) {
            // Busca el producto por SKU utilizando Optional
            Optional<ProductoModel> productoExistenteOpt = productoRepository.findBySku(productoCompra.getSku());

            if (productoExistenteOpt.isPresent()) {
                ProductoModel productoExistente = productoExistenteOpt.get();
                // Si el producto existe, suma la cantidad comprada al stock existente
                int nuevoStock = productoExistente.getStock() + productoCompra.getCantidad();
                productoExistente.setStock(nuevoStock); // Actualiza el stock
                productoRepository.save(productoExistente); // Guarda los cambios en la base de datos
            } else {
                // Si el producto no existe, crea uno nuevo
                ProductoModel nuevoProducto = new ProductoModel();
                nuevoProducto.setSku(productoCompra.getSku());
                nuevoProducto.setNombre("Nombre por definir"); // Se puede actualizar con los datos reales
                nuevoProducto.setDescripcion("Descripción por definir");
                nuevoProducto.setCategoria("Categoría por definir");
                nuevoProducto.setStock(productoCompra.getCantidad());
                nuevoProducto.setPrecioCompra(productoCompra.getPrecioUnitario()); // Establece el precio unitario recibido

                productoRepository.save(nuevoProducto); // Guarda el nuevo producto en la base de datos
            }
        }

        // Si todo sale bien, devuelve un mensaje de éxito
        return ResponseEntity.ok("Compra procesada con éxito.");
    }

    @PostMapping("/webhook/payment-confirmed")
    public ResponseEntity<String> receiveWebhook(@RequestBody WebhookPayload payload) {
        try {
            // Llamar al servicio para procesar la orden
            productoService.processWebhook(payload);
            return ResponseEntity.ok("Notificación de pago recibida y procesada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la notificación.");
        }
    }
}