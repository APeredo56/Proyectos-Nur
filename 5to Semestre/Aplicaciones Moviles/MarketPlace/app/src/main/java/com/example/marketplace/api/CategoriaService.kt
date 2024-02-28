package com.example.marketplace.api

import com.example.marketplace.models.Categoria
import com.example.marketplace.models.CategoriaDeleteResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
interface CategoriaService {
    @GET("categorias")
    fun getCategoriasList(): Call<List<Categoria>>


    @GET("categorias/{id}")
    fun getCategoriasById(@Path("id") id: Int): Call<Categoria>

    @PUT("categorias/{id}")
    fun editCategoria(@Path("id") id: Int, @Body body: Categoria) : Call<Categoria>

    @POST("categorias")
    fun insertCategoria(@Body body: Categoria): Call<Categoria>

    @DELETE("categorias/{id}")
    fun deleteCategoria(@Path("id")id: Int): Call<CategoriaDeleteResponse>

}