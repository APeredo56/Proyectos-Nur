package com.example.banco.api.model

class Beneficiario (
    val numeroCuenta: String,
    val ci: String,
    val nombreCompleto: String,
){
    override fun toString(): String {
        return "NumeroCuenta: $numeroCuenta, CI: $ci, NombreCompleto: $nombreCompleto"
    }
}