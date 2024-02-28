package com.example.banco.api.services

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

object AuthInterceptor : Interceptor {
    private var token : String = "";

    fun setToken(token: String){
        this.token = token
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if(request.header("No-Authentication")==null){

            if(!token.isNullOrEmpty())
            {
                val finalToken =  "Bearer $token"
                request = request.newBuilder()
                    .addHeader("Authorization",finalToken)
                    .build()
            } else {
                Log.e("Error", "Token nulo")
            }

        }

        return chain.proceed(request)
    }

}