package com.uade.tpo.e_commerce.controller;

import com.uade.tpo.e_commerce.dto.CarritoDTO;
import com.uade.tpo.e_commerce.dto.CarritoDetalleDTO;
import com.uade.tpo.e_commerce.dto.CarritoDetalleRequestDTO;
import com.uade.tpo.e_commerce.dto.CarritoRequestDTO;
import com.uade.tpo.e_commerce.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    /**
     * Crear un nuevo carrito para un usuario
     * POST /api/carritos
     */
    @PostMapping
    public ResponseEntity<CarritoDTO> createCarrito(@RequestBody CarritoRequestDTO dto) {
        return ResponseEntity.status(201).body(carritoService.createCarrito(dto));
    }

    /**
     * Obtener carrito por su ID
     * GET /api/carritos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTO> getCarritoById(@PathVariable Long id) {
        return ResponseEntity.ok(carritoService.getCarritoById(id));
    }

    /**
     * Obtener carrito de un usuario específico
     * GET /api/carritos/usuario/{usuarioId}
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoDTO> getCarritoByUsuarioId(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(carritoService.getCarritoByUsuarioId(usuarioId));
    }

    /**
     * Obtener todos los carritos
     * GET /api/carritos
     */
    @GetMapping
    public ResponseEntity<List<CarritoDTO>> getAllCarritos() {
        return ResponseEntity.ok(carritoService.getAllCarritos());
    }

    /**
     * Agregar una receta al carrito
     * POST /api/carritos/{id}/recetas
     */
    @PostMapping("/{id}/recetas")
    public ResponseEntity<CarritoDTO> agregarRecetaAlCarrito(
            @PathVariable Long id,
            @RequestBody CarritoDetalleRequestDTO dto) {
        return ResponseEntity.ok(carritoService.agregarRecetaAlCarrito(id, dto));
    }

    /**
     * Eliminar una receta del carrito
     * DELETE /api/carritos/{id}/recetas/{carritoDetalleId}
     */
    @DeleteMapping("/{id}/recetas/{carritoDetalleId}")
    public ResponseEntity<CarritoDTO> eliminarRecetaDelCarrito(
            @PathVariable Long id,
            @PathVariable Long carritoDetalleId) {
        return ResponseEntity.ok(carritoService.eliminarRecetaDelCarrito(id, carritoDetalleId));
    }

    /**
     * Actualizar cantidad de una receta en el carrito
     * PATCH /api/carritos/{id}/recetas/{carritoDetalleId}
     */
    @PatchMapping("/{id}/recetas/{carritoDetalleId}")
    public ResponseEntity<CarritoDetalleDTO> actualizarCantidadReceta(
            @PathVariable Long id,
            @PathVariable Long carritoDetalleId,
            @RequestParam Integer cantidad) {
        return ResponseEntity.ok(carritoService.actualizarCantidadReceta(id, carritoDetalleId, cantidad));
    }

    /**
     * Vaciar carrito (eliminar todas las recetas)
     * DELETE /api/carritos/{id}/vaciar
     */
    @DeleteMapping("/vaciar/{id}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long id) {
        carritoService.vaciarCarrito(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Eliminar un carrito
     * DELETE /api/carritos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrito(@PathVariable Long id) {
        carritoService.deleteCarrito(id);
        return ResponseEntity.noContent().build();
    }
}

