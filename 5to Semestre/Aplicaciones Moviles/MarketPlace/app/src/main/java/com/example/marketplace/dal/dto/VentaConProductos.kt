package com.example.marketplace.dal.dto

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class VentaConProductos(
    @Embedded val venta: Venta,
    @Relation(
        parentColumn = "ventaId",
        entityColumn = "productoId",
        associateBy = Junction(UnionVentaProducto::class)
    )
    val productos: List<Producto>
)