package com.example.marketplace.dal.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Venta (
    val cliente: String,
    val nit: Int,
    val usuario: String,
    val createdAt: String,
    val updatedAt: String,
        ){
    @PrimaryKey
    var ventaId: Int? = null
}