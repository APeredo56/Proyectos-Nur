package com.example.banco.api.services

import com.example.banco.api.model.Cuenta
import com.example.banco.api.model.CuentaAddResponse
import com.example.banco.api.model.Extracto
import com.example.banco.api.model.Movimiento
import com.example.banco.api.model.Transaccion
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CuentaService {

    @GET("accounts")
    fun fetchAccounts(): Call<List<Cuenta>>

    @GET("accounts/{id}/statement")
    fun fetchExtractos(@Path("id") id: Int): Call<List<Extracto>>

    @POST("accounts")
    fun createAccount(): Call<CuentaAddResponse>

    @POST("accounts/{id}/income")
    fun income(@Path("id") id: Int, @Body body: Transaccion): Call<Movimiento>

    @POST("accounts/{id}/expense")
    fun expense(@Path("id") id: Int, @Body body: Transaccion): Call<Movimiento>


}