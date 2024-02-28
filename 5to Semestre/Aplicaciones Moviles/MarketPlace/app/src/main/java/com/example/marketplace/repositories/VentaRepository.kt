package com.example.marketplace.repositories

import android.util.Log
import com.example.marketplace.api.VentaService
import com.example.marketplace.models.Producto
import com.example.marketplace.models.Venta
import com.example.marketplace.models.VentaDeleteResponse
import com.example.marketplace.models.VentaInsert
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object VentaRepository {

    fun fetchListaVentas(listener: VentaListListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val ventaService = retrofit.create(VentaService::class.java)
        val call = ventaService.getVentasList()
        call.enqueue(object : Callback<List<Venta>> {
            override fun onResponse(
                call: Call<List<Venta>>,
                response: Response<List<Venta>>
            ) {
                if (response.isSuccessful) {
                    val ventas = response.body()
                    ventas?.let {
                        listener.onFetchListaVentasSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Venta>>, t: Throwable) {
                listener.onFetchListaVentasError(t)
            }
        })
    }

    fun deleteVenta(id: Int, listener: VentaDeleteListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val ventaService = retrofit.create(VentaService::class.java)
        ventaService.deleteVenta(id)
            .enqueue(object : Callback<VentaDeleteResponse> {
                override fun onResponse(
                    call: Call<VentaDeleteResponse>,
                    response: Response<VentaDeleteResponse>
                ) {
                    if (response.isSuccessful) {
                        val respuesta = response.body()
                        respuesta?.let { listener.onVentaDeleteSuccess(it) }
                    }
                }

                override fun onFailure(call: Call<VentaDeleteResponse>, t: Throwable) {
                    listener.onVentaDeleteError(t)
                }
            })
    }

    fun fetchVentaDetail(id: Int, listener: VentaDetailListener) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val ventaService = retrofit.create(VentaService::class.java)
        ventaService.getVentasById(id).enqueue(object : Callback<Venta> {
            override fun onResponse(call: Call<Venta>, response: Response<Venta>) {
                if (response.isSuccessful) {
                    val venta = response.body()
                    venta?.let {
                        listener.onFetchVentaDetailFetched(it)
                    }
                }
            }

            override fun onFailure(call: Call<Venta>, t: Throwable) {
                listener.onFetchVentaDetailFetchError(t)
            }
        })
    }

    fun saveInsertVenta(
        nombre: String,
        nit: Int,
        usuario: String,
        productos: ArrayList<Producto>,
        listener: VentaInsertListener
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val body =
            VentaInsert(0, nombre, nit, usuario, productos)

        val ventaService = retrofit.create(VentaService::class.java)
        ventaService.insertVenta(body).enqueue(object : Callback<Venta> {
            override fun onResponse(call: Call<Venta>, response: Response<Venta>) {
                if (response.isSuccessful) {
                    val venta = response.body()
                    venta?.let {
                        listener.onVentaInsertSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<Venta>, t: Throwable) {
                listener.onVentaInsertError(t)
            }
        })
    }

    fun saveUpdateVenta(
        id: Int,
        nombre: String,
        nit: Int,
        usuario: String,
        created_at: String,
        updated_at: String,
        detalle: ArrayList<Producto>,
        listener: VentaUpdateListener
    ){
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val body =
            createVentaBody (nombre, nit, usuario, created_at, updated_at, detalle)

        val ventaService = retrofit.create(VentaService::class.java)
        ventaService.editVenta(id, body).enqueue(object : Callback<Venta> {
            override fun onResponse(call: Call<Venta>, response: Response<Venta>) {
                if (response.isSuccessful) {
                    val venta = response.body()
                    venta?.let {
                        listener.onVentaUpdateSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<Venta>, t: Throwable) {
                listener.onVentaUpdateError(t)
            }
        })
    }

    private fun createVentaBody(
        nombre: String,
        nit: Int,
        usuario: String,
        created_at: String,
        updated_at: String,
        detalle: ArrayList<Producto>
    ): RequestBody{
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("nombre", nombre)
            .addFormDataPart("nit", nit.toString())
            .addFormDataPart("usuario", usuario)
            .addFormDataPart("created_at", created_at)
            .addFormDataPart("updated_at", updated_at)
            .addFormDataPart("detalle", detalle.toString())
            .build()
    }


    interface VentaListListener {
        fun onFetchListaVentasSuccess(ventas: List<Venta>)
        fun onFetchListaVentasError(t: Throwable)
    }

    interface VentaDeleteListener {
        fun onVentaDeleteSuccess(ventaDeleteResponse: VentaDeleteResponse)
        fun onVentaDeleteError(t: Throwable)
    }

    interface VentaDetailListener {
        fun onFetchVentaDetailFetched(venta: Venta)
        fun onFetchVentaDetailFetchError(t: Throwable)
    }

    interface VentaInsertListener {
        fun onVentaInsertSuccess(venta: Venta)
        fun onVentaInsertError(t: Throwable)
    }

    interface VentaUpdateListener {
        fun onVentaUpdateSuccess(venta: Venta)
        fun onVentaUpdateError(t: Throwable)
    }
}