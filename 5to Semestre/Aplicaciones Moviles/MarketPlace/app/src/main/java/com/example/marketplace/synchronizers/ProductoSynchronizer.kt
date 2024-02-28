package com.example.marketplace.synchronizers

import android.content.Context
import android.util.Log
import com.example.marketplace.dal.conn.AppDatabase
import com.example.marketplace.models.Producto
import com.example.marketplace.repositories.ProductoRepository
import java.time.LocalDateTime

class ProductoSynchronizer (val context: Context, val productosApi: List<Producto>) :
    ProductoRepository.ProductoUpdateListener, ProductoRepository.ProductoInsertListener {
    private var db: AppDatabase = AppDatabase.getInstance(context)
    private var idConflictoDB = 0

    fun sync() {
        val productosDB =
            db.productoDao().getAll() as MutableList<com.example.marketplace.dal.dto.Producto>
        for (productoApi in productosApi) {
            val productoDB = productosDB.find { it.productoId == productoApi.id }
            if (productoDB == null) {
                //Estan en la api pero no en la db
                val newProducto = com.example.marketplace.dal.dto.Producto(
                    productoApi.nombre,
                    productoApi.descripcion,
                    productoApi.precio_actual,
                    productoApi.categoria.id,
                    productoApi.created_at,
                    productoApi.updated_at
                )
                newProducto.productoId = productoApi.id
                db.productoDao().insert(newProducto)
            } else if (productoDB.createdAt == productoApi.created_at) {
                // Actualizando informacion
                val updateApi = LocalDateTime.parse(
                    productoApi.updated_at.substring(0, 19)
                )
                val updateDB = LocalDateTime.parse(
                    productoDB.updatedAt.substring(0, 19)
                )
                if (updateApi == updateDB) {
                    //Esta actualizada
                    productosDB.remove(productoDB)
                    continue
                }
                if (updateApi.isAfter(updateDB)) {
                    val newProducto = com.example.marketplace.dal.dto.Producto(
                        productoApi.nombre,
                        productoApi.descripcion,
                        productoApi.precio_actual,
                        productoApi.categoria.id,
                        productoApi.created_at,
                        productoApi.updated_at
                    )
                    newProducto.productoId = productoApi.id
                    db.productoDao().update(newProducto)
                } else {
                    ProductoRepository.saveUpdateProducto(
                        productoDB.productoId!!,
                        productoDB.nombre,
                        "Sin descripcion",
                        productoDB.precio,
                        productoDB.idCategoria,
                        this
                    )
                }
                productosDB.remove(productoDB)
            } else {
                //Conflicto de ids
                idConflictoDB = productoDB.productoId!!
                db.productoDao().delete(productoDB)
                ProductoRepository.saveInsertProducto(
                    productoApi.nombre,
                    "Sin descripcion",
                    productoApi.precio_actual,
                    productoApi.categoria.id,
                    this
                )

                val newProductoDB = com.example.marketplace.dal.dto.Producto(
                    productoApi.nombre,
                    productoApi.descripcion,
                    productoApi.precio_actual,
                    productoApi.categoria.id,
                    productoApi.created_at,
                    productoApi.updated_at
                )
                newProductoDB.productoId = productoApi.id
                db.productoDao().insert(newProductoDB)
                productosDB.remove(productoDB)
            }
        }
        val lastDateApi = LocalDateTime.parse(productosApi.last().created_at.substring(0, 19))
        for(productoDB in productosDB){
            val dateCategoryDb = LocalDateTime.parse(productoDB.createdAt.substring(0, 19))
            if(lastDateApi.isBefore(dateCategoryDb)){
                idConflictoDB = productoDB.productoId!!
                db.productoDao().delete(productoDB)
                ProductoRepository.saveInsertProducto(
                    productoDB.nombre,
                    productoDB.descripcion,
                    productoDB.precio,
                    productoDB.idCategoria,
                    this
                )
            } else{
                db.productoDao().delete(productoDB)
            }
        }
    }

    override fun onProductoInsertSuccess(producto: Producto) {
        if (idConflictoDB != 0) {
            val newProducto = com.example.marketplace.dal.dto.Producto(
                producto.nombre,
                producto.descripcion,
                producto.precio_actual,
                producto.categoria.id,
                producto.created_at,
                producto.updated_at
            )
            newProducto.productoId = producto.id
            db.productoDao().insert(newProducto)
            idConflictoDB = 0
        }
    }


    override fun onProductoInsertError(t: Throwable) {

    }

    override fun onProductoUpdateSuccess(producto: Producto) {

    }

    override fun onProductoUpdateError(t: Throwable) {

    }
}