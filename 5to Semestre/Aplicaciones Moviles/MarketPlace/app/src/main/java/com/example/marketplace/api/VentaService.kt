package com.example.marketplace.api

import androidx.room.Delete
import com.example.marketplace.models.Venta
import com.example.marketplace.models.VentaDeleteResponse
import com.example.marketplace.models.VentaInsert
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface VentaService {

    @GET("ventas")
    fun getVentasList(): Call<List<Venta>>

    @GET("ventas/{id}")
    fun getVentasById(@Path("id") id: Int): Call<Venta>

    @PUT("ventas/{id}")
    fun editVenta(@Path("id") id: Int, @Body body: RequestBody) : Call<Venta>

    @POST("ventas")
    fun insertVenta(@Body body: VentaInsert): Call<Venta>

    @DELETE("ventas/{id}")
    fun deleteVenta(@Path("id")id: Int): Call<VentaDeleteResponse>
}