package com.example.banco.api.services

import com.example.banco.api.model.LoginModel
import com.example.banco.api.model.LoginResponse
import com.example.banco.api.model.RegisterModel
import com.example.banco.api.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AutenticacionService {

    @POST("login")
    @Headers("No-Authentication: true")
    fun login(@Body body: LoginModel): Call<LoginResponse>

    @POST("register")
    @Headers("No-Authentication: true")
    fun register(@Body body: RegisterModel): Call<RegisterResponse>
}