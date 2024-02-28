package com.example.marketplace.dal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.marketplace.dal.dto.Producto

@Dao
interface ProductoDao {
    @Query("SELECT * FROM producto")
    fun getAll(): List<Producto>

    @Query("SELECT * FROM producto WHERE productoId IN (:id)")
    fun getById(id: Int): Producto?

    @Query("SELECT * FROM producto WHERE idCategoria IN (:idCategoria)")
    fun getByCategoria(idCategoria: Int): List<Producto>?

    @Insert
    fun insert(user: Producto)

    @Update
    fun update(user: Producto)

    @Delete
    fun delete(user: Producto)
}