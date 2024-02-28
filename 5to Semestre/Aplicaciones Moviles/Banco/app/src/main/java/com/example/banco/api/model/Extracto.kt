package com.example.banco.api.model

class Extracto (
    val id: Int,
    val descripcion: String,
    val monto: Double,
    val tipo: Int,
    val cuentaOrigen: String,
    val cuentaDestino: String?,
    val created_at: String
        )