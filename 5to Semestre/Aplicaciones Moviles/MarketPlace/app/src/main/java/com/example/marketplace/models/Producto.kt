package com.example.marketplace.models

class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio_actual: Double,
    val created_at: String,
    val updated_at: String,
    val categoria: Categoria
){
    override fun toString(): String {
        return "Id: $id, Nombre: $nombre, Descripcion: $descripcion, Precio: $precio_actual, Categoria: ${categoria.nombre}"
    }
}