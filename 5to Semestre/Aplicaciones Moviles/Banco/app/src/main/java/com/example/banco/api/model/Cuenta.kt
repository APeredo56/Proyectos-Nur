package com.example.banco.api.model

class Cuenta (
    var id: Int,
    var numero: String,
    var saldo: Double,
    var userId: Int
        ){
    override fun toString(): String {
        return "Cuenta(id=$id, numero='$numero', saldo=$saldo, userId=$userId)"
    }
}