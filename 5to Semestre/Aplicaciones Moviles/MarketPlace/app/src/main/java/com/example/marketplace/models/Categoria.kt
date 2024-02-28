package com.example.marketplace.models

class Categoria(
    val id: Int,
    val nombre: String,
    val created_at: String,
    val updated_at: String,
    val productos: ArrayList<Producto>
)