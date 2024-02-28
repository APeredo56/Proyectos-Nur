package com.example.banco.api.repositories

import android.util.Log
import com.example.banco.api.model.LoginModel
import com.example.banco.api.model.LoginResponse
import com.example.banco.api.model.RegisterModel
import com.example.banco.api.model.RegisterResponse
import com.example.banco.api.services.AutenticacionService
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


object AutenticacionRepository {

    fun login(email: String, password: String, success: (LoginResponse) -> Unit,
              failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val autenticacionService = retrofit.create(AutenticacionService::class.java)
        val body = LoginModel(email, password)
        autenticacionService.login(body).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    loginResponse?.let {
                        success(it)
                    }
                } else{
                    failure(Throwable("Error al iniciar sesión"))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun register(body: RegisterModel, success: (RegisterResponse) -> Unit,
                 failure: (Throwable) -> Unit){
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val autenticacionService = retrofit.create(AutenticacionService::class.java)
        autenticacionService.register(body).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    registerResponse?.let {
                        success(it)
                    }
                } else{
                    displayApiResponseErrorBody(response)
                    failure(Throwable("Error al registrar"))
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
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