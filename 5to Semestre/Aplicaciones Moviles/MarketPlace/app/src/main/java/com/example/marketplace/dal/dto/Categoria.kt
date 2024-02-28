package com.example.marketplace.dal.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categoria(
    val nombre: String,
    val createdAt: String,
    val updatedAt: String
) {
    @PrimaryKey
    var id: Int? = null
}
