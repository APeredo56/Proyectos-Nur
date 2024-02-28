package com.example.banco.api.repositories

import android.util.Log
import com.example.banco.api.model.Transferencia
import com.example.banco.api.services.TransferenciaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


object TransferenciaRepository {

    fun realizarTransferencia(
        body: Transferencia,
        success: (Transferencia) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val transferenciaService = retrofit.create(TransferenciaService::class.java)
        transferenciaService.realizarTransferencia(body).enqueue(object : Callback<Transferencia> {
            override fun onResponse(call: Call<Transferencia>, response: Response<Transferencia>) {
                if (response.isSuccessful) {
                    val transaccion = response.body()

                    transaccion?.let {
                        success(it)

                    }
                } else {
                    displayApiResponseErrorBody(response)
                    failure(Throwable("Error al realizar transferencia"))
                }
            }

            override fun onFailure(call: Call<Transferencia>, t: Throwable) {
                failure(t)
            }

        })
    }

    fun displayApiResponseErrorBody(response: Response<*>) {
        val i = response.errorBody()!!.byteStream()
        val r = BufferedReader(InputStreamReader(i))
        val errorResult = StringBuilder()
        var line: String?
        try {
            while (r.readLine().also { line = it } != null) {
                errorResult.append(line).append('\n')
            }
            Log.d("API_RESPONSE_ERROR_BODY", errorResult.toString())
            println(errorResult)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}