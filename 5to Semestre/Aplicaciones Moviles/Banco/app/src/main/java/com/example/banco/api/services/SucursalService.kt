package com.example.banco.api.services

import com.example.banco.api.model.Sucursal
import retrofit2.Call
import retrofit2.http.GET

interface SucursalService {

    @GET("branches")
    fun fetchBranches(): Call<List<Sucursal>>
}