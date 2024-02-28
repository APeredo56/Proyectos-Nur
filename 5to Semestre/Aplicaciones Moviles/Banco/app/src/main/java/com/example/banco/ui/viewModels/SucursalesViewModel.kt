package com.example.banco.ui.viewModels

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.banco.api.model.Sucursal
import com.example.banco.api.repositories.SucursalRepository

class SucursalesViewModel : ViewModel() {
    private var _isMyLocationEnabled = MutableLiveData(false)
    private var _listaSucursales = MutableLiveData<ArrayList<Sucursal>>(arrayListOf())

    val isMyLocationEnabled get() = _isMyLocationEnabled
    val listaSucursales get() = _listaSucursales

    fun verifyLocationEnabled(context: Context, activity: Activity) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
            _isMyLocationEnabled.value = false
            return
        }
        _isMyLocationEnabled.value = true
    }

    fun fetchListaSucursales(context: Context) {
        SucursalRepository.fetchSucursales({
            Log.e("fetchListaSucursales", it.toString())
            _listaSucursales.value = it as ArrayList<Sucursal>
        }, {
            Toast.makeText(
                context,
                "Ocurrio un error al obtener las sucursales",
                Toast.LENGTH_SHORT
            ).show()
        })
    }


}