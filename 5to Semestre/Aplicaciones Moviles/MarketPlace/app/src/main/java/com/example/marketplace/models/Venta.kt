package com.example.marketplace.models

class Venta (
    val id: Int,
    val nombre: String,
    val nit: Int,
    val usuario: String,
    val created_at: String,
    val updated_at: String,
    val detalle: ArrayList<Detalle>
        ){
    override fun toString(): String {
        return "Id: $id, Nombre: $nombre, Nit: $nit, Usuario: $usuario, productos: ${detalle}"
    }
}