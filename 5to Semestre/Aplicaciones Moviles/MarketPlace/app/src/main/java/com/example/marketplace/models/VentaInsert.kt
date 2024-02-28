package com.example.marketplace.models

class VentaInsert (
    val id: Int,
    val nombre: String,
    val nit: Int,
    val usuario: String,
    val productos: ArrayList<Producto>
        )