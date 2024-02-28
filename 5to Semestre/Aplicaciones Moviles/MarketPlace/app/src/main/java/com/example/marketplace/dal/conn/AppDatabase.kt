package com.example.marketplace.dal.conn

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marketplace.dal.dao.CategoriaDao
import com.example.marketplace.dal.dao.ProductoDao
import com.example.marketplace.dal.dao.UnionVentaProductoDao
import com.example.marketplace.dal.dao.VentaDao
import com.example.marketplace.dal.dto.Categoria
import com.example.marketplace.dal.dto.Producto
import com.example.marketplace.dal.dto.UnionVentaProducto
import com.example.marketplace.dal.dto.Venta
import com.example.marketplace.dal.dto.VentaConProductos

@Database(
    entities = [Categoria::class, Producto::class, Venta::class, UnionVentaProducto::class],
    version = 6
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao
    abstract fun categoriaDao(): CategoriaDao
    abstract fun VentaDao(): VentaDao
    abstract fun UnionVentaProductoDao(): UnionVentaProductoDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "marketplace"
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

