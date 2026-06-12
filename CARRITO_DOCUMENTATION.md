# 🛒 Estructura de Carrito - Documentación

## Resumen
Se ha creado una estructura completa de carrito para la aplicación de e-commerce que permite:
- Asociar un carrito a un usuario
- Agregar recetas al carrito
- Cada receta incluye sus ingredientes asociados
- Gestionar cantidades y precios

---

## 📦 Entidades Creadas

### 1. **Carrito** (`model/Carrito.java`)
```java
- idCarrito: Long (PK)
- usuario: Usuario (ManyToOne)
- detalles: List<CarritoDetalle> (OneToMany)
```

**Características:**
- Cada usuario puede tener un único carrito activo
- Relación One-to-Many con CarritoDetalle para manejo de items
- Cascada de eliminación automática de detalles

### 2. **CarritoDetalle** (`model/CarritoDetalle.java`)
```java
- idCarritoDetalle: Long (PK)
- carrito: Carrito (ManyToOne)
- receta: Receta (ManyToOne)
- cantidad: Integer
- precioTotal: Double
```

**Características:**
- Tabla intermedia que relaciona Carrito con Receta
- Almacena cantidad de recetas en el carrito
- Calcula el precio total automáticamente

---

## 📋 DTOs Creados

### 1. **CarritoDTO** (`dto/CarritoDTO.java`)
Representa un carrito completo con información del usuario y detalles

```json
{
  "id": 1,
  "usuarioId": 1,
  "usuarioEmail": "usuario@example.com",
  "precioTotal": 150.50,
  "detalles": [...]
}
```

### 2. **CarritoRequestDTO** (`dto/CarritoRequestDTO.java`)
Para crear un nuevo carrito

```json
{
  "usuarioId": 1
}
```

### 3. **CarritoDetalleDTO** (`dto/CarritoDetalleDTO.java`)
Representa un item del carrito con sus ingredientes

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
      "idRecetaDetalle": 1,
      "cantidad": 100,
      "unidad": "g",
      "idReceta": 5,
      "idIngrediente": 10
    }
  ]
}
```

### 4. **CarritoDetalleRequestDTO** (`dto/CarritoDetalleRequestDTO.java`)
Para agregar/actualizar recetas en el carrito

```json
{
  "recetaId": 5,
  "cantidad": 2
}
```

---

## 🗂️ Repositorios Creados

### 1. **CarritoRepository** (`repository/CarritoRepository.java`)
```java
JpaRepository<Carrito, Long>
- findByUsuarioIdUsuario(Long usuarioId): Optional<Carrito>
```

### 2. **CarritoDetalleRepository** (`repository/CarritoDetalleRepository.java`)
```java
JpaRepository<CarritoDetalle, Long>
- findByCarritoIdCarritoAndRecetaIdReceta(Long carritoId, Long recetaId): Optional<CarritoDetalle>
```

---

## 🔧 Servicios Creados

### **CarritoService** (`service/CarritoService.java`)

#### Métodos principales:

| Método | Descripción |
|--------|-------------|
| `createCarrito(CarritoRequestDTO)` | Crea un carrito nuevo para un usuario |
| `getCarritoById(Long id)` | Obtiene carrito por ID |
| `getCarritoByUsuarioId(Long usuarioId)` | Obtiene carrito de un usuario |
| `getAllCarritos()` | Lista todos los carritos |
| `agregarRecetaAlCarrito(Long carritoId, CarritoDetalleRequestDTO)` | Agrega receta al carrito (incrementa cantidad si ya existe) |
| `eliminarRecetaDelCarrito(Long carritoId, Long carritoDetalleId)` | Elimina receta del carrito |
| `actualizarCantidadReceta(Long carritoId, Long carritoDetalleId, Integer cantidad)` | Actualiza cantidad |
| `vaciarCarrito(Long carritoId)` | Elimina todas las recetas del carrito |
| `deleteCarrito(Long carritoId)` | Elimina el carrito |

**Validaciones:**
- Verifica que el usuario no tenga carrito previo antes de crear uno
- Valida que las recetas existan
- Aumenta cantidad si receta ya está en carrito
- Calcula precios totales automáticamente

---

## 🌐 Endpoints del Controller

### **CarritoController** (`controller/CarritoController.java`)

#### Crear Carrito
```
POST /api/carritos
Content-Type: application/json

{
  "usuarioId": 1
}

Response: 201 Created
```

#### Obtener Carrito por ID
```
GET /api/carritos/{id}

Response: 200 OK (CarritoDTO)
```

#### Obtener Carrito de un Usuario
```
GET /api/carritos/usuario/{usuarioId}

Response: 200 OK (CarritoDTO)
```

#### Listar Todos los Carritos
```
GET /api/carritos

Response: 200 OK (List<CarritoDTO>)
```

#### Agregar Receta al Carrito
```
POST /api/carritos/{id}/recetas
Content-Type: application/json

{
  "recetaId": 5,
  "cantidad": 2
}

Response: 200 OK (CarritoDTO)

Comportamiento:
- Si receta ya existe en carrito: incrementa cantidad
- Si no existe: crea nuevo detalle
```

#### Eliminar Receta del Carrito
```
DELETE /api/carritos/{id}/recetas/{carritoDetalleId}

Response: 200 OK (CarritoDTO)
```

#### Actualizar Cantidad de Receta
```
PATCH /api/carritos/{id}/recetas/{carritoDetalleId}?cantidad=3

Response: 200 OK (CarritoDetalleDTO)
```

#### Vaciar Carrito
```
DELETE /api/carritos/{id}/vaciar

Response: 204 No Content
```

#### Eliminar Carrito
```
DELETE /api/carritos/{id}

Response: 204 No Content
```

---

## 📊 Relaciones de Base de Datos

```
┌─────────────────┐
│   USUARIOS      │
├─────────────────┤
│ id_usuario (PK) │
│ email           │◄─────────┐
│ ...             │          │
└─────────────────┘          │ 1:N
                             │
                      ┌──────────────────┐
                      │   CARRITOS       │
                      ├──────────────────┤
                      │ id_carrito (PK)  │
                      │ usuario_id (FK)  │
                      └──────────────────┘
                             │
                             │ 1:N
                             │
                      ┌──────────────────────┐
                      │  CARRITO_DETALLES    │
                      ├──────────────────────┤
                      │ id_carrito_det. (PK) │
                      │ carrito_id (FK)      │
                      │ receta_id (FK)       │
                      │ cantidad             │
                      │ precio_total         │
                      └──────────────────────┘
                             │
                             │ N:1
                             │
                      ┌──────────────────┐
                      │    RECETAS       │
                      ├──────────────────┤
                      │ id_receta (PK)   │
                      │ nombre           │
                      │ precio_receta    │
                      └──────────────────┘
                             │
                             │ 1:N
                             │
                      ┌──────────────────────┐
                      │ RECETA_DETALLES      │
                      ├──────────────────────┤
                      │ id_receta_det. (PK)  │
                      │ receta_id (FK)       │
                      │ ingrediente_id (FK)  │
                      │ cantidad             │
                      │ unidad               │
                      └──────────────────────┘
                             │
                             │ N:1
                             │
                      ┌──────────────────┐
                      │ INGREDIENTES     │
                      ├──────────────────┤
                      │ id_ingrediente   │
                      │ nombre           │
                      │ precio           │
                      │ stock            │
                      └──────────────────┘
```

---

## 🧪 Casos de Uso

### 1. Crear Carrito para Usuario
```
1. POST /api/carritos { usuarioId: 1 }
2. Response: Carrito vacío creado
```

### 2. Agregar Receta al Carrito
```
1. POST /api/carritos/1/recetas { recetaId: 5, cantidad: 2 }
2. Se agregan 2 pizzas (receta 5) al carrito
3. Automáticamente se cargan ingredientes de la receta
```

### 3. Aumentar Cantidad de Receta Existente
```
1. POST /api/carritos/1/recetas { recetaId: 5, cantidad: 1 }
2. Sistema detecta que receta 5 ya está en carrito
3. Incrementa cantidad de 2 a 3
```

### 4. Ver Carrito Completo
```
1. GET /api/carritos/usuario/1
2. Response: CarritoDTO con detalles y todos los ingredientes
```

### 5. Modificar Cantidad
```
1. PATCH /api/carritos/1/recetas/10?cantidad=5
2. Actualiza cantidad a 5 y recalcula precio total
```

---

## ✨ Características Implementadas

✅ Asociación usuario-carrito (1:1 activo)  
✅ Múltiples recetas por carrito  
✅ Ingredientes cargados con cada receta  
✅ Cálculo automático de precios totales  
✅ Incremento automático de cantidad si receta ya existe  
✅ Validaciones de negocio  
✅ Manejo de excepciones  
✅ Cascada de eliminación  
✅ Transaccionalidad  
✅ DTOs completos para peticiones y respuestas  

---

## 🔍 Notas Importantes

1. **Validación de Cantidad**: Si intentas actualizar cantidad a 0 o negativo, se lanza excepción
2. **Integridad**: No se pueden agregar recetas que no existan
3. **Aislamiento**: Cada usuario tiene su propio carrito
4. **Cálculo Automático**: Los precios se calculan automáticamente basados en cantidad × precio receta
5. **Lazy Loading**: Los detalles se cargan bajo demanda para optimizar rendimiento

---

## 🚀 Próximas Mejoras (Opcionales)

- [ ] Validar stock de ingredientes al agregar recetas
- [ ] Historial de cambios en carrito
- [ ] Descuentos y promociones
- [ ] Sincronización con pedidos
- [ ] Persistencia de carrito abandonado

