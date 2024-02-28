package com.example.marketplace.dal.dto

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Producto(
    var nombre: String,
    var descripcion: String,
    val precio: Double,
    val idCategoria: Int,
    val createdAt: String,
    val updatedAt: String,
) {
    @PrimaryKey
    var productoId: Int? = null
}
