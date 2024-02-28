package com.example.marketplace.dal.dto

import androidx.room.Entity

@Entity(primaryKeys = ["ventaId", "productoId"])
data class UnionVentaProducto(
    val ventaId: Int,
    val productoId: Int,
    var cantidad: Int
)