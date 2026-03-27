package tpo_uade.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpo_uade.e_commerce.model.Producto;
import tpo_uade.e_commerce.service.ProductoService;



@RestController
// para acceder a este controlador, la URL base será /api/productos
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // https://localhost:8080/api/productos -> ejecutar este método y devolver un mensaje ejemplo
    @GetMapping
    public List<Producto> getAllListaProductos() {
        return productoService.getAllProductos();
    }

 
    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }
 
    // del http://localhost:8080/api/productos/1 -> elimina el producto con id 1
    @DeleteMapping("/{id}")
    public void deleteProductoById(@PathVariable Long id) {
        productoService.deleteProductoById(id);
    }

    // para crear un nuevo producto, se envía un JSON con los datos del producto al endpoint http://localhost:8080/api/productos con el método POST
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto created = productoService.saveProducto(producto);
        return ResponseEntity.status(201).body(created);
    }

    //para actualizar un producto, se envía un JSON con los datos actualizados al endpoint http://localhost:8080/api/productos/{id} con el método PUT
     @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto body) {
        Producto existing = productoService.getProductoById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        existing.setNombre(body.getNombre());
        existing.setDescripcion(body.getDescripcion());
        return ResponseEntity.ok(productoService.saveProducto(existing));
    }


}


