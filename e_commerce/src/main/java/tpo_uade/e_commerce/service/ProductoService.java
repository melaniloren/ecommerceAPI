package tpo_uade.e_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tpo_uade.e_commerce.model.Producto;
import tpo_uade.e_commerce.repository.ProductoRepository;

@Service
@Transactional
public class ProductoService {
 
    @Autowired
    private ProductoRepository productoRepository;
    
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public void deleteProductoById(Long id) {
    productoRepository.deleteById(id);
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto saveProducto(Producto producto) {
    return productoRepository.save(producto);
    }


}
