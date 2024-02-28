package com.example.banco.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.banco.R
import com.example.banco.api.services.AuthInterceptor

class LoadingActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_loading)

        checkForToken()
    }

    private fun checkForToken() {
        //wait for 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val token = getSharedPreferences("auth", MODE_PRIVATE)
                .getString("token", null)
            Log.d("TOKEN", token.toString())
            if (token == null) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, MainActivity::class.java)
                AuthInterceptor.setToken(token)
                startActivity(intent)
            }
            finish()
        }, 2000)
    }
}