package com.uade.tpo.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.e_commerce.model.DetallePedido;
import com.uade.tpo.e_commerce.service.DetallePedidoService;


@RestController
@RequestMapping("/api/detalle-pedidos")
public class DetallePedidoController {
    @Autowired
    private DetallePedidoService detallePedidoService;


    @GetMapping
    public List<DetallePedido> getAllListaDetallePedido() {
        return detallePedidoService.getAllDetallesPedidos();
    }

 
    @GetMapping("/{id}")
    public DetallePedido getProductoById(@PathVariable Long id) {
        return detallePedidoService.getDetallePedidoById(id);
    }
 

    @DeleteMapping("/{id}")
    public void deletePDetallePedidoById(@PathVariable Long id) {
        detallePedidoService.deleteDetallePedidoById(id);
    }

    // para crear un nuevo producto, se envía un JSON con los datos del producto al endpoint http://localhost:8080/api/productos con el método POST
    @PostMapping
    public ResponseEntity<DetallePedido> createDetallePediod(@RequestBody DetallePedido detallePedido) {
        DetallePedido created = detallePedidoService.saveDetallePedido(detallePedido);
        return ResponseEntity.status(201).body(created);
    }

    //para actualizar un producto, se envía un JSON con los datos actualizados al endpoint http://localhost:8080/api/productos/{id} con el método PUT
    //  @PutMapping("/{id}")
    // public ResponseEntity<DetallePedido> updateDetallePedido(@PathVariable Long id, @RequestBody DetallePedido body) {
    //     DetallePedido existing = detallePedidoService.getDetallePedidoById(id);
    //     if (existing == null) return ResponseEntity.notFound().build();
    //     existing.setNombre(body.getNombre());
    //     existing.setDescripcion(body.getDescripcion());
    //     return ResponseEntity.ok(detallePedidoService.saveDetallePedido(existing));
    // } 

}
