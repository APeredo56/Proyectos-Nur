package com.example.banco.api.services

import com.example.banco.api.model.Beneficiario
import com.example.banco.api.model.BeneficiarioResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BeneficiarioService {

    @GET("recipients")
    fun fetchBeneficiarios(): Call<List<BeneficiarioResponse>>

    @POST("recipients")
    fun addBeneficiario(@Body beneficiario: Beneficiario): Call<BeneficiarioResponse>
}