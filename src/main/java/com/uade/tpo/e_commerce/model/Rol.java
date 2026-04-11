package com.uade.tpo.e_commerce.model;

/**
 * Define los roles disponibles en el sistema.
 * ADMIN: tiene acceso completo al sistema, puede gestionar usuarios y productos.
 * VENDEDOR: puede publicar productos para vender.
 * COMPRADOR: puede comprar productos.
 */
enum Rol {
    ADMIN,
    VENDEDOR,
    COMPRADOR
}