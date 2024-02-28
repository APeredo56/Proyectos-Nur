package com.example.banco.api.repositories

import com.example.banco.api.model.PagoQRModel
import com.example.banco.api.model.PagoQRResponse
import com.example.banco.api.model.QRModel
import com.example.banco.api.model.QRResponse
import com.example.banco.api.services.QRService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object QRRepository {

    fun crearQR(qr: QRModel, success: (QRResponse) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val qrService = retrofit.create(QRService::class.java)
        qrService.generarQR(qr).enqueue(object : Callback<QRResponse> {
            override fun onResponse(call: Call<QRResponse>, response: Response<QRResponse>) {
                if (response.isSuccessful) {
                    val qr = response.body()
                    qr?.let {
                        success(it)
                    }
                } else {
                    failure(Throwable("Error al crear QR"))
                }
            }

            override fun onFailure(call: Call<QRResponse>, t: Throwable) {
                failure(Throwable("Error al crear QR"))
            }

        })

    }

    fun pagarQR(qr: PagoQRModel, success: (PagoQRResponse) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val qrService = retrofit.create(QRService::class.java)
        qrService.pagarQR(qr).enqueue(object : Callback<PagoQRResponse> {
            override fun onResponse(call: Call<PagoQRResponse>, response: Response<PagoQRResponse>) {
                if (response.isSuccessful) {
                    val qr = response.body()
                    qr?.let {
                        success(it)
                    }
                } else {
                    failure(Throwable("Error al pagar QR"))
                }
            }

            override fun onFailure(call: Call<PagoQRResponse>, t: Throwable) {
                failure(Throwable("Error al pagar QR"))
            }

        })

    }
}