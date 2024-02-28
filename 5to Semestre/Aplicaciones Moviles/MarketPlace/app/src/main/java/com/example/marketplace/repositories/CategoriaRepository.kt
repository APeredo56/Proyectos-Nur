package com.example.marketplace.repositories

import android.util.Log
import com.example.marketplace.api.CategoriaService
import com.example.marketplace.models.Categoria
import com.example.marketplace.models.Producto
import com.example.marketplace.models.CategoriaDeleteResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CategoriaRepository {

    fun fetchListaCategorias(listener: CategoriaListListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val categoriaService = retrofit.create(CategoriaService::class.java)
        categoriaService.getCategoriasList().enqueue(object : Callback<List<Categoria>> {
            override fun onResponse(
                call: Call<List<Categoria>>,
                response: Response<List<Categoria>>
            ) {
                if (response.isSuccessful) {
                    val categorias = response.body()
                    categorias?.let {
                        listener.onCategoriaListFetched(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Categoria>>, t: Throwable) {
                listener.onCategoriaListFetchError(t)
            }
        })


    }

    fun deleteCategoria(id: Int, listener: CategoriaDeleteListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val categoriaService = retrofit.create(CategoriaService::class.java)
        categoriaService.deleteCategoria(id)
            .enqueue(object : Callback<CategoriaDeleteResponse> {
                override fun onResponse(
                    call: Call<CategoriaDeleteResponse>,
                    response: Response<CategoriaDeleteResponse>
                ) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()
                        respuesta?.let { listener.onCategoriaDeleteSuccess(it) }
                    }
                }

                override fun onFailure(call: Call<CategoriaDeleteResponse>, t: Throwable) {
                    listener.onCategoriaDeleteError(t)
                }
            })
    }

    fun fetchCategoriaDetail(id: Int, listener: CategoriaDetailListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val categoriaService = retrofit.create(CategoriaService::class.java)
        categoriaService.getCategoriasById(id).enqueue(object : Callback<Categoria> {
            override fun onResponse(call: Call<Categoria>, response: Response<Categoria>) {
                if (response.isSuccessful) {
                    val categoria = response.body()
                    categoria?.let { listener.onCategoriaDetailFetched(it) }
                }
            }

            override fun onFailure(call: Call<Categoria>, t: Throwable) {
                listener.onCategoriaDetailFetchError(t)
            }
        })
    }

    fun saveInsertCategoria(
        nombre: String,
        listener: CategoriaInsertListener
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body = Categoria(0, nombre, "", "", arrayListOf())

        val categoriaService = retrofit.create(CategoriaService::class.java)
        categoriaService.insertCategoria(body).enqueue(object : Callback<Categoria> {
            override fun onResponse(call: Call<Categoria>, response: Response<Categoria>) {
                if (response.isSuccessful) {
                    val categoria = response.body()
                    categoria?.let { listener.onCategoriaInsertSuccess(it) }
                }
            }

            override fun onFailure(call: Call<Categoria>, t: Throwable) {
                listener.onCategoriaInsertError(t)
            }
        })
    }

    fun saveUpdateCategoria(
        id: Int,
        nombre: String,
        listener: CategoriaUpdateListener
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val body = Categoria(id, nombre, "", "", arrayListOf())
        val categoriaService = retrofit.create(CategoriaService::class.java)
        categoriaService.editCategoria(id, body).enqueue(object : Callback<Categoria> {
            override fun onResponse(call: Call<Categoria>, response: Response<Categoria>) {
                if (response.isSuccessful) {
                    val categoria = response.body()
                    categoria?.let { listener.onCategoriaUpdateSuccess(it) }
                }
            }

            override fun onFailure(call: Call<Categoria>, t: Throwable) {
                listener.onCategoriaUpdateError(t)
            }
        })
    }

    interface CategoriaListListener {
        fun onCategoriaListFetched(categorias: List<Categoria>)
        fun onCategoriaListFetchError(t: Throwable)
    }

    interface CategoriaDeleteListener {
        fun onCategoriaDeleteSuccess(categoriaDeleteResponse: CategoriaDeleteResponse)
        fun onCategoriaDeleteError(t: Throwable)
    }

    interface CategoriaDetailListener {
        fun onCategoriaDetailFetched(categoria: Categoria)
        fun onCategoriaDetailFetchError(t: Throwable)
    }

    interface CategoriaInsertListener {
        fun onCategoriaInsertSuccess(categoria: Categoria)
        fun onCategoriaInsertError(t: Throwable)
    }

    interface CategoriaUpdateListener {
        fun onCategoriaUpdateSuccess(categoria: Categoria)
        fun onCategoriaUpdateError(t: Throwable)
    }
}