package com.example.banco.api.services

import com.example.banco.api.model.PagoQRModel
import com.example.banco.api.model.PagoQRResponse
import com.example.banco.api.model.QRModel
import com.example.banco.api.model.QRResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface QRService {

    @POST("qr/generate")
    fun generarQR(@Body qr: QRModel): Call<QRResponse>

    @POST("qr/pay")
    fun pagarQR(@Body qr: PagoQRModel): Call<PagoQRResponse>
}