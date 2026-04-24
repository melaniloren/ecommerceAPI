package com.uade.tpo.e_commerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.uade.tpo.e_commerce.dto.PedidoDTO;
import com.uade.tpo.e_commerce.dto.PedidoRequestDTO;
import com.uade.tpo.e_commerce.exception.PedidoNotFoundException;
import com.uade.tpo.e_commerce.model.Pedido;
import com.uade.tpo.e_commerce.model.Usuario;
import com.uade.tpo.e_commerce.repository.PedidoRepository;
import com.uade.tpo.e_commerce.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {
 
    // private Long getCurrentUserId() {
    //     return ((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUsuario();
    // }

    @Autowired
    private PedidoRepository pedidoRepository;
    
// AÑADIMOS el repositorio de Usuario para poder buscarlo en la Base de Datos
    @Autowired
    private UsuarioRepository usuarioRepository;

    // CREAMOS un método de ayuda para obtener el Usuario real
    // private Usuario getUsuarioAutenticado() {
    //     // Obtenemos el texto del token (suele ser el email o el username)
    //     String usernameOEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
    //     // Buscamos el usuario en la base de datos. 
    //     // IMPORTANTE: Si en tu UsuarioRepository el método se llama findByUsername, cámbialo aquí.
    //     return usuarioRepository.findByEmail(usernameOEmail)
    //             .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado en la base de datos"));
    // }

private Usuario getUsuarioAutenticado() {
        String usernameOEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        // Agregamos esto para ver que esta pasando en la consola 
        System.out.println("Buscando en BD el usuario del token: " + usernameOEmail);
        
        return usuarioRepository.findByEmail(usernameOEmail) // <-- Ojo, revisa si debe ser findByUsername
                .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado en la base de datos"));
    }

    // ACTUALIZAMOS este método para usar nuestro nuevo ayudante
    private Long getCurrentUserId() {
        return getUsuarioAutenticado().getIdUsuario();
    }

    public List<PedidoDTO> getAllPedidos() {
        return pedidoRepository.findAll()
                .stream()
.map(pedido -> new PedidoDTO(
                        pedido.getIdPedido(),
                        pedido.getFecha(),
                        pedido.getTotal(),
                        pedido.getUsuario() != null ? pedido.getUsuario().getIdUsuario() : null))

                .collect(Collectors.toList());
    }

    public PedidoDTO getPedidoById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);

        if (pedido == null) {
           throw new PedidoNotFoundException("pedido", id);
        }

        PedidoDTO pedidoDTO = new PedidoDTO(
                pedido.getIdPedido(),
                pedido.getFecha(),
                pedido.getTotal(),
                pedido.getUsuario() != null ? pedido.getUsuario().getIdUsuario() : null);


        return pedidoDTO;
    }

    public void deletePedidoById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido not found"));
        if (!pedido.getUsuario().getIdUsuario().equals(getCurrentUserId())) {
            throw new PedidoNotFoundException("pedido", id);
        }
        pedidoRepository.delete(pedido);
    }

    public PedidoDTO savePedido(PedidoRequestDTO pedidoRequestDTO) {

        Pedido pedido = Pedido.builder()
                .fecha(pedidoRequestDTO.getFecha())
                .total(pedidoRequestDTO.getTotal())
                .build();
        // Asignamos el usuario real que obtuvimos de la BD
        pedido.setUsuario(getUsuarioAutenticado());

        Pedido pedidoAdd = pedidoRepository.save(pedido);

        return new PedidoDTO(
                pedidoAdd.getIdPedido(),
                pedidoAdd.getFecha(),
                pedidoAdd.getTotal(),
                pedidoAdd.getUsuario() != null ? pedidoAdd.getUsuario().getIdUsuario() : null);
    }

    public PedidoDTO updatePedido(Long id, PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new PedidoNotFoundException( "pedido", id));
        if (!pedido.getUsuario().getIdUsuario().equals(getCurrentUserId())) {
            throw new PedidoNotFoundException( "pedido", id);
        }

        pedido.setFecha(pedidoRequestDTO.getFecha());
        pedido.setTotal(pedidoRequestDTO.getTotal());

        Pedido pedidoActualizado = pedidoRepository.save(pedido);

        return new PedidoDTO(
                pedidoActualizado.getIdPedido(),
                pedidoActualizado.getFecha(),
                pedidoActualizado.getTotal(),
                pedidoActualizado.getUsuario() != null ? pedidoActualizado.getUsuario().getIdUsuario() : null);
    }
}
