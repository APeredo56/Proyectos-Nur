package com.example.banco.api.repositories

import android.util.Log
import com.example.banco.api.model.Cuenta
import com.example.banco.api.model.CuentaAddResponse
import com.example.banco.api.model.Extracto
import com.example.banco.api.model.Movimiento
import com.example.banco.api.model.Transaccion
import com.example.banco.api.repositories.AutenticacionRepository.displayApiResponseErrorBody
import com.example.banco.api.services.CuentaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CuentaRepository {

    var listaCuentas: ArrayList<Cuenta>? = null


    fun fetchCuentas(success: (List<Cuenta>) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val cuentaService = retrofit.create(CuentaService::class.java)
        cuentaService.fetchAccounts().enqueue(object : Callback<List<Cuenta>> {
            override fun onResponse(call: Call<List<Cuenta>>, response: Response<List<Cuenta>>) {
                if (response.isSuccessful) {
                    val cuentas = response.body()
                    cuentas?.let {
                        success(it)
                        listaCuentas = it as ArrayList<Cuenta>
                    }
                } else {
                    displayApiResponseErrorBody(response)
                    failure(Throwable("Error al obtener cuentas"))
                }
            }

            override fun onFailure(call: Call<List<Cuenta>>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun createCuenta(success: (CuentaAddResponse) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val cuentaService = retrofit.create(CuentaService::class.java)
        cuentaService.createAccount().enqueue(object : Callback<CuentaAddResponse> {
            override fun onResponse(
                call: Call<CuentaAddResponse>,
                response: Response<CuentaAddResponse>
            ) {
                if (response.isSuccessful) {
                    val cuenta = response.body()
                    cuenta?.let {
                        success(it)
                    }
                } else {
                    displayApiResponseErrorBody(response)
                    failure(Throwable("Error al crear cuenta"))
                }
            }

            override fun onFailure(call: Call<CuentaAddResponse>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun fetchExtracto(id: Int, success: (List<Extracto>) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val cuentaService = retrofit.create(CuentaService::class.java)
        cuentaService.fetchExtractos(id).enqueue(object : Callback<List<Extracto>> {
            override fun onResponse(
                call: Call<List<Extracto>>,
                response: Response<List<Extracto>>
            ) {
                if (response.isSuccessful) {
                    val cuenta = response.body()
                    cuenta?.let {
                        success(it)

                    }
                } else {
                    displayApiResponseErrorBody(response)
                    failure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<List<Extracto>>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun registrarIngreso(
        id: Int, descripcion: String,
        monto: Double,
        success: (Movimiento) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        Log.e("id", id.toString())
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val cuentaService = retrofit.create(CuentaService::class.java)
        val body = Transaccion(descripcion, monto)
        cuentaService.income(id, body).enqueue(object : Callback<Movimiento> {
            override fun onResponse(call: Call<Movimiento>, response: Response<Movimiento>) {
                if (response.isSuccessful) {
                    val movimiento = response.body()
                    movimiento?.let {
                        success(it)
                    }
                } else {
                    displayApiResponseErrorBody(response)
                    failure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<Movimiento>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun registrarEgreso(
        id: Int,
        descripcion: String,
        monto: Double,
        success: (Movimiento) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val cuentaService = retrofit.create(CuentaService::class.java)
        val body = Transaccion(descripcion, monto)
        cuentaService.expense(id, body).enqueue(object : Callback<Movimiento> {
            override fun onResponse(call: Call<Movimiento>, response: Response<Movimiento>) {
                if (response.isSuccessful) {
                    val movimiento = response.body()
                    movimiento?.let {
                        success(it)
                    }
                } else {
                    displayApiResponseErrorBody(response)
                    failure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<Movimiento>, t: Throwable) {
                failure(t)
            }
        })
    }
}