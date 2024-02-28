package com.example.marketplace.dal.dto

import androidx.room.Embedded
import androidx.room.Relation

data class CategoriaConProducto(
    @Embedded val category: Categoria,
    @Relation(
        parentColumn = "id",
        entityColumn = "idCategoria"
    )
    val productos: List<Producto>

)