package com.example.banco.api.repositories

import com.example.banco.api.services.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepository {
    companion object {
        private var client = OkHttpClient.Builder().addInterceptor(AuthInterceptor).build()
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("http://apibancomoviles1.jmacboy.com/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}