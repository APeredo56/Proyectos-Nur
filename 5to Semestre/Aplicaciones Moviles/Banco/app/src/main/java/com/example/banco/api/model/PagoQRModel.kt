package com.example.banco.api.model

class PagoQRModel (
    var codigo: String,
    var cuentaOrigen: String
    ){
    override fun toString(): String {
        return "PagoQRModel(codigo='$codigo', cuentaOrigen='$cuentaOrigen')"
    }
}