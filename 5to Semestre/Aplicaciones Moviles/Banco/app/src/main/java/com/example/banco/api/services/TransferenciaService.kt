package com.example.banco.api.services

import com.example.banco.api.model.Transferencia
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TransferenciaService {

    @POST("accounts/transfer")
    fun realizarTransferencia(@Body body: Transferencia): Call<Transferencia>
}