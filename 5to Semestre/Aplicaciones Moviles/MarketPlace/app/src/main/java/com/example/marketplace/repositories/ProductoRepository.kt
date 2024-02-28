package com.example.marketplace.repositories

import android.util.Log
import com.example.marketplace.api.ProductoService
import com.example.marketplace.models.Categoria
import com.example.marketplace.models.Producto
import com.example.marketplace.models.ProductoDeleteResponse
import com.example.marketplace.models.ProductoInsert
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ProductoRepository {

    fun fetchListaProductos(listener: ProductoListListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val productoService = retrofit.create(ProductoService::class.java)
        val call = productoService.getProductosList()
        call.enqueue(object : Callback<List<Producto>> {
            override fun onResponse(
                call: Call<List<Producto>>,
                response: Response<List<Producto>>
            ) {
                if (response.isSuccessful) {
                    val productos = response.body()
                    productos?.let {
                        listener.onFetchListaProductosSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {
                listener.onFetchListaProductosError(t)
            }
        })
    }

    fun deleteProducto(id: Int, listener: ProductoDeleteListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val productoService = retrofit.create(ProductoService::class.java)
        productoService.deleteProducto(id)
            .enqueue(object : Callback<ProductoDeleteResponse> {
                override fun onResponse(
                    call: Call<ProductoDeleteResponse>,
                    response: Response<ProductoDeleteResponse>
                ) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()
                        respuesta?.let { listener.onProductoDeleteSuccess(it) }
                    }
                }

                override fun onFailure(call: Call<ProductoDeleteResponse>, t: Throwable) {
                    listener.onProductoDeleteError(t)
                }
            })
    }

    fun fetchProductoDetail(id: Int, listener: ProductoDetailListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val productoService = retrofit.create(ProductoService::class.java)
        productoService.getProductosById(id).enqueue(object : Callback<Producto> {
            override fun onResponse(call: Call<Producto>, response: Response<Producto>) {
                if (response.isSuccessful) {
                    val producto = response.body()
                    producto?.let { listener.onProductoDetailFetched(it) }
                }
            }

            override fun onFailure(call: Call<Producto>, t: Throwable) {
                listener.onProductoDetailFetchError(t)
            }
        })
    }

    fun saveInsertProducto(
        nombre: String,
        descripcion: String,
        precio: Double,
        idCategoria: Int,
        listener: ProductoInsertListener
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body =
            ProductoInsert(0, nombre, descripcion, precio, idCategoria)

        val productoService = retrofit.create(ProductoService::class.java)
        productoService.insertProducto(body).enqueue(object : Callback<Producto> {
            override fun onResponse(call: Call<Producto>, response: Response<Producto>) {
                if (response.isSuccessful) {
                    val producto = response.body()
                    producto?.let { listener.onProductoInsertSuccess(it) }
                }
            }

            override fun onFailure(call: Call<Producto>, t: Throwable) {
                listener.onProductoInsertError(t)
            }
        })
    }

    fun saveUpdateProducto(
        id: Int,
        nombre: String,
        descripcion: String,
        precio: Double,
        idCategoria: Int,
        listener: ProductoUpdateListener
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body =
            ProductoInsert(id, nombre, descripcion, precio, idCategoria)

        val productoService = retrofit.create(ProductoService::class.java)
        productoService.editProducto(id, body).enqueue(object : Callback<Producto> {
            override fun onResponse(call: Call<Producto>, response: Response<Producto>) {
                if (response.isSuccessful) {
                    val producto = response.body()
                    producto?.let { listener.onProductoUpdateSuccess(it) }
                }
            }

            override fun onFailure(call: Call<Producto>, t: Throwable) {
                listener.onProductoUpdateError(t)
            }
        })
    }

    interface ProductoListListener {
        fun onFetchListaProductosSuccess(productos: List<Producto>)
        fun onFetchListaProductosError(t: Throwable)
    }

    interface ProductoDeleteListener {
        fun onProductoDeleteSuccess(response: ProductoDeleteResponse)
        fun onProductoDeleteError(t: Throwable)
    }

    interface ProductoDetailListener {
        fun onProductoDetailFetched(producto: Producto)
        fun onProductoDetailFetchError(t: Throwable)
    }

    interface ProductoInsertListener {
        fun onProductoInsertSuccess(producto: Producto)
        fun onProductoInsertError(t: Throwable)
    }

    interface ProductoUpdateListener {
        fun onProductoUpdateSuccess(producto: Producto)
        fun onProductoUpdateError(t: Throwable)
    }
}