package com.example.marketplace.api

import com.example.marketplace.models.Producto
import com.example.marketplace.models.ProductoDeleteResponse
import com.example.marketplace.models.ProductoInsert
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductoService {
    @GET("productos")
    fun getProductosList(): Call<List<Producto>>


    @GET("productos/{id}")
    fun getProductosById(@Path("id") id: Int): Call<Producto>

    @PUT("productos/{id}")
    fun editProducto(@Path("id") id: Int, @Body body: ProductoInsert) : Call<Producto>

    @POST("productos")
    fun insertProducto(@Body body: ProductoInsert): Call<Producto>

    @POST("productos/{id}")
    fun deleteProducto(@Path("id")id: Int): Call<ProductoDeleteResponse>
}