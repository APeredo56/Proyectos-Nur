package com.example.banco.api.model

class BeneficiarioResponse (
    val numeroCuenta: String,
    val ci: String,
    val nombreCompleto: String,
    val userId: Int,
    val created_at: String,
    val updated_at: String,
    val id: Int
        ){
    override fun toString(): String {
        return "NumeroCuenta: $numeroCuenta, CI: $ci, NombreCompleto: $nombreCompleto, UserId: $userId, Id: $id"
    }
}