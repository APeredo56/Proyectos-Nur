package com.example.marketplace.dal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.marketplace.dal.dto.UnionVentaProducto

@Dao
interface UnionVentaProductoDao {
    @Query("SELECT * FROM UnionVentaProducto WHERE ventaId = :idVenta AND productoId = :idProducto")
    fun getById(idVenta: Int, idProducto: Int): UnionVentaProducto?
    @Insert
    fun insert(venta: UnionVentaProducto)
    @Delete
    fun delete(venta: UnionVentaProducto)
    @Update
    fun update(venta: UnionVentaProducto)
    @Query("DELETE FROM UnionVentaProducto WHERE ventaId = :idVenta")
    fun deleteByVentaId(idVenta: Int)

}