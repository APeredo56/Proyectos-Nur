package com.example.banco.api.model

class Transferencia(
    val descripcion: String,
    val monto: Double,
    val cuentaOrigen: String,
    val beneficiario: Int
) {
    override fun toString(): String {
        return "Descripcion: $descripcion, Monto: $monto, CuentaOrigen: $cuentaOrigen, Beneficiario: $beneficiario"
    }
}

