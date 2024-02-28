package com.example.banco.api.model

class Movimiento(
    val descripcion: String,
    val monto: Double,
    val tipo: Int,
    val cuentaOrigen: String,
    val cuentaDestino: String,
    val created_at: String,
    val id: Int
)