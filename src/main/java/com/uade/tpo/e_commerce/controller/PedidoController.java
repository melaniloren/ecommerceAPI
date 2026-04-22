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

import com.uade.tpo.e_commerce.dto.PedidoDTO;
import com.uade.tpo.e_commerce.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

    // https://localhost:8080/api/pedidos -> ejecutar este método y devolver un mensaje ejemplo
    @GetMapping
    public List<PedidoDTO> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    //  http://localhost:8080/api/pedido/1 -> devuelve el pedido con id 1
    @GetMapping("/{id}")
    public PedidoDTO getPedidoById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id);
    }

    // del http://localhost:8080/api/pedido/1 -> elimina el pedido con id 1
@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidoById(@PathVariable Long id) {
        pedidoService.deletePedidoById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> createPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO created = pedidoService.savePedido(pedidoDTO);
        return ResponseEntity.status(201).body(created);
    }
    
}
