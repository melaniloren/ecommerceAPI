# 🛒 Carrito - Endpoints Rápidos

## URL Base: `/api/carritos`

### 1️⃣ CREAR CARRITO
```http
POST /api/carritos
Content-Type: application/json

{
  "usuarioId": 1
}

Respuesta: 201 Created
{
  "id": 1,
  "usuarioId": 1,
  "usuarioEmail": "usuario@example.com",
  "precioTotal": 0,
  "detalles": []
}
```

---

### 2️⃣ OBTENER CARRITO
```http
GET /api/carritos/{id}

Ejemplos:
- GET /api/carritos/1        (por ID del carrito)
- GET /api/carritos/usuario/1 (por ID del usuario)
```

---

### 3️⃣ LISTAR TODOS LOS CARRITOS
```http
GET /api/carritos
```

---

### 4️⃣ AGREGAR RECETA AL CARRITO ⭐
```http
POST /api/carritos/{id}/recetas
Content-Type: application/json

{
  "recetaId": 5,
  "cantidad": 2
}

Respuesta: 200 OK
{
  "id": 1,
  "usuarioId": 1,
  "usuarioEmail": "usuario@example.com",
  "precioTotal": 100.00,
  "detalles": [
    {
      "id": 1,
      "recetaId": 5,
      "recetaNombre": "Pizza Margarita",
      "recetaPrecio": 50.00,
      "cantidad": 2,
      "precioTotal": 100.00,
      "ingredientes": [
        {
          "idRecetaDetalle": 1,
          "cantidad": 100,
          "unidad": "g",
          "idReceta": 5,
          "idIngrediente": 10
        }
      ]
    }
  ]
}

NOTA: Si la receta ya existe en el carrito, incrementa la cantidad automáticamente
```

---

### 5️⃣ ACTUALIZAR CANTIDAD
```http
PATCH /api/carritos/{carritoId}/recetas/{carritoDetalleId}?cantidad=5

Respuesta: 200 OK
{
  "id": 1,
  "recetaId": 5,
  "recetaNombre": "Pizza Margarita",
  "recetaPrecio": 50.00,
  "cantidad": 5,
  "precioTotal": 250.00,
  "ingredientes": [...]
}
```

---

### 6️⃣ ELIMINAR RECETA DEL CARRITO
```http
DELETE /api/carritos/{carritoId}/recetas/{carritoDetalleId}

Respuesta: 200 OK (CarritoDTO actualizado)
```

---

### 7️⃣ VACIAR CARRITO (Eliminar todas las recetas)
```http
DELETE /api/carritos/{id}/vaciar

Respuesta: 204 No Content
```

---

### 8️⃣ ELIMINAR CARRITO
```http
DELETE /api/carritos/{id}

Respuesta: 204 No Content
```

---

## 🎯 Flujo Típico

```
1. POST /api/carritos               → Crear carrito para usuario
2. POST /api/carritos/1/recetas     → Agregar Pizza (cantidad 2)
3. POST /api/carritos/1/recetas     → Agregar Hamburguesa (cantidad 3)
4. GET /api/carritos/1              → Ver carrito completo
5. PATCH /api/carritos/1/recetas/1  → Cambiar cantidad de Pizza a 5
6. DELETE /api/carritos/1           → Vaciar carrito
7. DELETE /api/carritos/1           → Eliminar carrito
```

---

## 🏗️ Estructura de Datos

### CarritoDTO
```json
{
  "id": 1,
  "usuarioId": 1,
  "usuarioEmail": "email@example.com",
  "precioTotal": 250.00,
  "detalles": [...]
}
```

### CarritoDetalleDTO
```json
{
  "id": 1,
  "recetaId": 5,
  "recetaNombre": "Pizza Margarita",
  "recetaPrecio": 50.00,
  "cantidad": 2,
  "precioTotal": 100.00,
  "ingredientes": [
    {
      "idRecetaDetalle": 10,
      "cantidad": 100,
      "unidad": "gramos",
      "idReceta": 5,
      "idIngrediente": 3
    }
  ]
}
```

---

## ⚠️ Validaciones

✓ No puedes crear dos carritos para el mismo usuario  
✓ No puedes agregar recetas que no existan  
✓ Cantidad debe ser mayor a 0  
✓ PrecioTotal se calcula automáticamente  
✓ Si agregas receta que ya existe, incrementa cantidad  

---

## 📊 Base de Datos

### Tabla: carritos
```sql
CREATE TABLE carritos (
  id_carrito BIGINT PRIMARY KEY AUTO_INCREMENT,
  usuario_id BIGINT NOT NULL UNIQUE,
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id_usuario)
);
```

### Tabla: carrito_detalles
```sql
CREATE TABLE carrito_detalles (
  id_carrito_detalle BIGINT PRIMARY KEY AUTO_INCREMENT,
  carrito_id BIGINT NOT NULL,
  receta_id BIGINT NOT NULL,
  cantidad INT NOT NULL,
  precio_total DOUBLE,
  FOREIGN KEY (carrito_id) REFERENCES carritos(id_carrito),
  FOREIGN KEY (receta_id) REFERENCES recetas(id_receta)
);
```

---

## ✅ Estado: COMPLETADO

✓ Modelos creados  
✓ DTOs creados  
✓ Repositorios creados  
✓ Services implementados  
✓ Controllers creados  
✓ Compilación exitosa  
✓ Documentación completa  

