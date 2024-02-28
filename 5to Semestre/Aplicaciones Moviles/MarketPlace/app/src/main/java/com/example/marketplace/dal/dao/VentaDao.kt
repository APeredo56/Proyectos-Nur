package com.example.marketplace.dal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.marketplace.dal.dto.Venta
import com.example.marketplace.dal.dto.VentaConProductos

@Dao
interface VentaDao {
    @Query("SELECT * FROM Venta")
    fun getAll(): List<Venta>

    @Query("SELECT * FROM venta WHERE ventaId IN (:id)")
    fun getById(id: Int): Venta?

    @Transaction
    @Query("SELECT * FROM Venta")
    fun getAllVentasConProductos(): List<VentaConProductos>

    @Transaction
    @Query("SELECT * FROM Venta where ventaId = :id")
    fun getVentaConProductos(id: Int): List<VentaConProductos>

    @Insert
    fun insert(user: Venta) : Long

    @Update
    fun update(user: Venta)

    @Delete
    fun delete(user: Venta)
}