package com.uade.tpo.e_commerce.service;

import com.uade.tpo.e_commerce.dto.CarritoDTO;
import com.uade.tpo.e_commerce.dto.CarritoDetalleDTO;
import com.uade.tpo.e_commerce.dto.CarritoDetalleRequestDTO;
import com.uade.tpo.e_commerce.dto.CarritoRequestDTO;
import com.uade.tpo.e_commerce.exception.RecetaNotFoundException;
import com.uade.tpo.e_commerce.model.Carrito;
import com.uade.tpo.e_commerce.model.CarritoDetalle;
import com.uade.tpo.e_commerce.model.Receta;
import com.uade.tpo.e_commerce.model.Usuario;
import com.uade.tpo.e_commerce.repository.CarritoDetalleRepository;
import com.uade.tpo.e_commerce.repository.CarritoRepository;
import com.uade.tpo.e_commerce.repository.RecetaRepository;
import com.uade.tpo.e_commerce.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoDetalleRepository carritoDetalleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private RecetaDetalleService recetaDetalleService;

    /**
     * Crea un nuevo carrito para un usuario
     */
    public CarritoDTO createCarrito(CarritoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        // Verificar si el usuario ya tiene carrito
        carritoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).ifPresent(carrito -> {
            throw new IllegalArgumentException("El usuario ya tiene un carrito activo");
        });

        Carrito carrito = Carrito.builder()
                .usuario(usuario)
                .detalles(List.of())
                .build();

        Carrito guardado = carritoRepository.save(carrito);
        return toDTO(guardado);
    }

    /**
     * Obtiene el carrito de un usuario específico
     */
    public CarritoDTO getCarritoByUsuarioId(Long usuarioId) {
        Carrito carrito = carritoRepository.findByUsuarioIdUsuario(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado para el usuario"));
        return toDTO(carrito);
    }

    /**
     * Obtiene un carrito por su ID
     */
    public CarritoDTO getCarritoById(Long id) {
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado"));
        return toDTO(carrito);
    }

    /**
     * Obtiene todos los carritos
     */
    public List<CarritoDTO> getAllCarritos() {
        return carritoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Agrega una receta al carrito
     */
    public CarritoDTO agregarRecetaAlCarrito(Long carritoId, CarritoDetalleRequestDTO dto) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado"));

        Receta receta = recetaRepository.findById(dto.getRecetaId())
                .orElseThrow(() -> new RecetaNotFoundException(dto.getRecetaId()));

        // Verificar si la receta ya está en el carrito
        var detalleExistente = carritoDetalleRepository.findByCarritoIdCarritoAndRecetaIdReceta(carritoId, dto.getRecetaId());

        if (detalleExistente.isPresent()) {
            // Si ya existe, incrementar cantidad
            CarritoDetalle detalle = detalleExistente.get();
            detalle.setCantidad(detalle.getCantidad() + dto.getCantidad());
            detalle.setPrecioTotal(detalle.getPrecioTotal() + (receta.getPrecioReceta() * dto.getCantidad()));
            carritoDetalleRepository.save(detalle);
        } else {
            // Si no existe, crear nuevo
            CarritoDetalle detalle = CarritoDetalle.builder()
                    .carrito(carrito)
                    .receta(receta)
                    .cantidad(dto.getCantidad())
                    .precioTotal(receta.getPrecioReceta() * dto.getCantidad())
                    .build();
            carritoDetalleRepository.save(detalle);
        }

        // Recargar para obtener los detalles actualizados
        Carrito carritoActualizado = carritoRepository.findById(carritoId).orElseThrow();
        return toDTO(carritoActualizado);
    }

    /**
     * Elimina una receta del carrito
     */
    public CarritoDTO eliminarRecetaDelCarrito(Long carritoId, Long carritoDetalleId) {
        CarritoDetalle detalle = carritoDetalleRepository.findById(carritoDetalleId)
                .orElseThrow(() -> new EntityNotFoundException("Detalle de carrito no encontrado"));

        if (!detalle.getCarrito().getIdCarrito().equals(carritoId)) {
            throw new IllegalArgumentException("El detalle no pertenece al carrito especificado");
        }

        carritoDetalleRepository.delete(detalle);

        Carrito carrito = carritoRepository.findById(carritoId).orElseThrow();
        return toDTO(carrito);
    }

    /**
     * Actualiza la cantidad de una receta en el carrito
     */
    public CarritoDetalleDTO actualizarCantidadReceta(Long carritoId, Long carritoDetalleId, Integer nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        CarritoDetalle detalle = carritoDetalleRepository.findById(carritoDetalleId)
                .orElseThrow(() -> new EntityNotFoundException("Detalle de carrito no encontrado"));

        if (!detalle.getCarrito().getIdCarrito().equals(carritoId)) {
            throw new IllegalArgumentException("El detalle no pertenece al carrito especificado");
        }

        detalle.setCantidad(nuevaCantidad);
        detalle.setPrecioTotal(detalle.getReceta().getPrecioReceta() * nuevaCantidad);
        CarritoDetalle actualizado = carritoDetalleRepository.save(detalle);

        return toCarritoDetalleDTO(actualizado);
    }

    /**
     * Vacía el carrito eliminando todos sus detalles
     */
    public void vaciarCarrito(Long carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado"));
        carrito.getDetalles().clear();
        carritoRepository.save(carrito);
    }

    /**
     * Elimina un carrito
     */
    public void deleteCarrito(Long carritoId) {
        carritoRepository.deleteById(carritoId);
    }

    /**
     * Convierte una entidad Carrito a CarritoDTO
     */
    private CarritoDTO toDTO(Carrito carrito) {
        Double precioTotal = carrito.getDetalles().stream()
                .mapToDouble(CarritoDetalle::getPrecioTotal)
                .sum();

        return CarritoDTO.builder()
                .id(carrito.getIdCarrito())
                .usuarioId(carrito.getUsuario().getIdUsuario())
                .usuarioEmail(carrito.getUsuario().getEmail())
                .precioTotal(precioTotal)
                .detalles(carrito.getDetalles().stream()
                        .map(this::toCarritoDetalleDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    /**
     * Convierte una entidad CarritoDetalle a CarritoDetalleDTO
     */
    private CarritoDetalleDTO toCarritoDetalleDTO(CarritoDetalle detalle) {
        return CarritoDetalleDTO.builder()
                .id(detalle.getIdCarritoDetalle())
                .recetaId(detalle.getReceta().getIdReceta())
                .recetaNombre(detalle.getReceta().getNombre())
                .recetaPrecio(detalle.getReceta().getPrecioReceta())
                .cantidad(detalle.getCantidad())
                .precioTotal(detalle.getPrecioTotal())
                .ingredientes(detalle.getReceta().getRecetaDetalles().stream()
                        .map(recetaDetalleService::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}

