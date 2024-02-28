package com.example.banco.api.repositories

import android.util.Log
import com.example.banco.api.model.Sucursal
import com.example.banco.api.repositories.AutenticacionRepository.displayApiResponseErrorBody
import com.example.banco.api.services.SucursalService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SucursalRepository {

    fun fetchSucursales(success : (List<Sucursal>) -> Unit, failure : (Throwable) -> Unit){
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val sucursalService = retrofit.create(SucursalService::class.java)
        sucursalService.fetchBranches().enqueue(object : Callback<List<Sucursal>> {
            override fun onResponse(
                call: Call<List<Sucursal>>,
                response: Response<List<Sucursal>>
            ) {
                if(response.isSuccessful){
                    val sucursales = response.body()
                    sucursales?.let {
                        success(it)
                    }
                }else{
                    displayApiResponseErrorBody(response)
                    Log.e("SucursalRepository", response.toString())
                    failure(Throwable("Error al obtener sucursales"))
                }
            }

            override fun onFailure(call: Call<List<Sucursal>>, t: Throwable) {
                failure(t)
            }
        })
    }
}