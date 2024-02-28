package com.example.marketplace.synchronizers

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.marketplace.dal.conn.AppDatabase
import com.example.marketplace.models.Categoria
import com.example.marketplace.models.Producto
import com.example.marketplace.repositories.CategoriaRepository
import com.example.marketplace.repositories.ProductoRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CategoriaSynchronizer(
    val context: Context, val categoriasApi: List<Categoria>
) :
    CategoriaRepository.CategoriaInsertListener, CategoriaRepository.CategoriaUpdateListener,
    ProductoRepository.ProductoInsertListener {
    private var db: AppDatabase = AppDatabase.getInstance(context)
    var productos = arrayListOf<com.example.marketplace.dal.dto.Producto>()
    private var idConflictoDB = 0

    fun sync() {
        val categoriasDB =
            db.categoriaDao().getAll() as MutableList<com.example.marketplace.dal.dto.Categoria>
        for (categoriaApi in categoriasApi) {
            val categoriaDB = categoriasDB.find { it.id == categoriaApi.id }
            if (categoriaDB == null) {
                //Estan en la api pero no en la db
                val newCategoria = com.example.marketplace.dal.dto.Categoria(
                    categoriaApi.nombre,
                    categoriaApi.created_at,
                    categoriaApi.updated_at
                )
                newCategoria.id = categoriaApi.id
                db.categoriaDao().insert(newCategoria)
            } else if (categoriaDB.createdAt == categoriaApi.created_at) {
                // Actualizando informacion
                val updateApi = LocalDateTime.parse(
                    categoriaApi.updated_at.substring(0, 19)
                )
                val updateDB = LocalDateTime.parse(
                    categoriaDB.updatedAt.substring(0, 19)
                )
                if (updateApi == updateDB) {
                    //Esta actualizada
                    categoriasDB.remove(categoriaDB)
                    continue
                }
                if (updateApi.isAfter(updateDB)) {
                    val newCategoria = com.example.marketplace.dal.dto.Categoria(
                        categoriaApi.nombre,
                        categoriaApi.created_at,
                        categoriaApi.updated_at
                    )
                    newCategoria.id = categoriaApi.id
                    db.categoriaDao().update(newCategoria)
                } else {
                    CategoriaRepository.saveUpdateCategoria(
                        categoriaDB.id!!,
                        categoriaDB.nombre,
                        this
                    )
                }
                categoriasDB.remove(categoriaDB)
            } else {
                // Conflicto de ids, se elimina la categoria de la db y se la inserta en la api con
                // un nuevo id
                idConflictoDB = categoriaDB.id!!
                db.categoriaDao().delete(categoriaDB)
                CategoriaRepository.saveInsertCategoria(categoriaDB.nombre, this)

                //Se guarda la categoria de la api en la posicion original
                val newCategoriaDB = com.example.marketplace.dal.dto.Categoria(
                    categoriaApi.nombre,
                    categoriaApi.created_at,
                    categoriaApi.updated_at
                )
                newCategoriaDB.id = categoriaApi.id
                db.categoriaDao().insert(newCategoriaDB)
                categoriasDB.remove(categoriaDB)
            }
        }
        //Estan en la base de datos pero no en la api

        for (categoriaDB in categoriasDB) {
            val lastDateApi = LocalDateTime.parse(categoriasApi.last().created_at.substring(0, 19))
            val dateCategoryDb = LocalDateTime.parse(categoriaDB.createdAt.substring(0, 19))
            if (lastDateApi.isBefore(dateCategoryDb)) {
                productos =
                    db.categoriaDao().getProductoWithCategoriaById(categoriaDB.id!!).productos
                            as ArrayList<com.example.marketplace.dal.dto.Producto>
                idConflictoDB = categoriaDB.id!!
                db.categoriaDao().delete(categoriaDB)
                CategoriaRepository.saveInsertCategoria(
                    categoriaDB.nombre,
                    this
                )

            } else {
                productos = db.categoriaDao()
                    .getProductoWithCategoriaById(categoriaDB.id!!).productos as
                        ArrayList<com.example.marketplace.dal.dto.Producto>
                db.categoriaDao().delete(categoriaDB)
                for (producto in productos){
                    db.productoDao().delete(producto)
                }

            }
        }
    }

    override fun onCategoriaInsertSuccess(categoria: Categoria) {
        if (idConflictoDB != 0) {
            val newCategoria = com.example.marketplace.dal.dto.Categoria(
                categoria.nombre,
                categoria.created_at,
                categoria.updated_at
            )
            newCategoria.id = categoria.id
            db.categoriaDao().insert(newCategoria)
            for (producto in productos) {
                val updatedProduct = com.example.marketplace.dal.dto.Producto(
                    producto.nombre,
                    producto.descripcion,
                    producto.precio,
                    categoria.id,
                    producto.createdAt,
                    producto.updatedAt
                )
                updatedProduct.productoId = producto.productoId
                db.productoDao().update(updatedProduct)
            }
            idConflictoDB = 0
        }
    }

    override fun onCategoriaInsertError(t: Throwable) {
        Log.d("Error al insertar", t.message.toString())
    }

    override fun onProductoInsertSuccess(producto: Producto) {

    }

    override fun onProductoInsertError(t: Throwable) {

    }

    override fun onCategoriaUpdateSuccess(categoria: Categoria) {

    }

    override fun onCategoriaUpdateError(t: Throwable) {
        Toast.makeText(context, "Error al actualizar categoria", Toast.LENGTH_SHORT).show()
    }
}
