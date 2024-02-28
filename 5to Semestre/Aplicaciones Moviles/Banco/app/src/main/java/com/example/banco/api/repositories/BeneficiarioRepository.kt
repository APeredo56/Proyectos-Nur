package com.example.banco.api.repositories

import com.example.banco.api.model.Beneficiario
import com.example.banco.api.model.BeneficiarioResponse
import com.example.banco.api.services.BeneficiarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object BeneficiarioRepository {
    var listaBeneficiarios: ArrayList<BeneficiarioResponse>? = null

    fun fetchBeneficiarios(success: (List<BeneficiarioResponse>) -> Unit, failure: () -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val beneficiarioService = retrofit.create(BeneficiarioService::class.java)
        beneficiarioService.fetchBeneficiarios().enqueue(object :
            Callback<List<BeneficiarioResponse>> {
            override fun onResponse(
                call: Call<List<BeneficiarioResponse>>,
                response: Response<List<BeneficiarioResponse>>
            ) {
                if (response.isSuccessful) {
                    listaBeneficiarios = response.body() as ArrayList<BeneficiarioResponse>
                    val beneficiarios = response.body()
                    beneficiarios?.let {
                        success(it)
                    }
                } else {
                    failure()
                }
            }

            override fun onFailure(call: Call<List<BeneficiarioResponse>>, t: Throwable) {
                failure()
            }
        })
    }

    fun insertBeneficiario(
        beneficiario: Beneficiario,
        success: (List<BeneficiarioResponse>) -> Unit,
        failure: () -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val beneficiarioService = retrofit.create(BeneficiarioService::class.java)
        beneficiarioService.addBeneficiario(beneficiario).enqueue(object :
            Callback<BeneficiarioResponse> {
            override fun onResponse(
                call: Call<BeneficiarioResponse>,
                response: Response<BeneficiarioResponse>
            ) {
                if (response.isSuccessful) {
                    listaBeneficiarios?.add(response.body() as BeneficiarioResponse)
                    val beneficiarios = response.body()
                    beneficiarios?.let {
                        success(listaBeneficiarios as List<BeneficiarioResponse>)
                    }
                } else {
                    failure()
                }
            }

            override fun onFailure(call: Call<BeneficiarioResponse>, t: Throwable) {
                failure()
            }
        })
    }

    fun getBeneficiarios(): ArrayList<BeneficiarioResponse>? {
        return listaBeneficiarios
    }

}