# TODO: Enable Users to Delete Only Their Own Pedidos

## Steps:

1. [x] Add `@ManyToOne Usuario usuario;` to Pedido.java + DB migration
2. [x] Add `Long usuarioId;` to PedidoDTO.java 
3. [x] Update PedidoService.java:
   - Inject SecurityContextHolder
   - savePedido: set pedido.setUsuario(current user)
   - deletePedidoById: check ownership, throw if not owner
4. [x] Update PedidoController.java: Return ResponseEntity.noContent()
5. [x] SecurityConfig.java: Change DELETE /api/pedidos/** to .authenticated()
6. [x] Create exception: PedidoNotOwnedException.java
7. **[ ]** Run DB migration SQL
8. **[ ]** Test: mvn spring-boot:run, Postman tests for create/delete own/other

**Note:** Admins can still delete any via role check or no check needed.

Updated after each step.
