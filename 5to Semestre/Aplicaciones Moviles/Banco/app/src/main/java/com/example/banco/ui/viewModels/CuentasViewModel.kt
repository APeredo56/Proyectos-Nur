package com.example.banco.ui.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.banco.api.model.Cuenta
import com.example.banco.api.model.Extracto
import com.example.banco.api.repositories.CuentaRepository

class CuentasViewModel : ViewModel(){

    private var _listaCuentas = MutableLiveData<ArrayList<Cuenta>>(arrayListOf())
    private var _errorCuenta = MutableLiveData("")
    private var _cuentaActual = MutableLiveData<Cuenta>()
    private var _listaExtractos = MutableLiveData<ArrayList<Extracto>>(arrayListOf())

    val listaCuentas: LiveData<ArrayList<Cuenta>> get() = _listaCuentas
    val errorCuenta get() = _errorCuenta
    val cuentaActual get() = _cuentaActual
    val listaExtractos get() = _listaExtractos

    fun fetchCuentas(){
        CuentaRepository.fetchCuentas({
            _listaCuentas.value = it as ArrayList<Cuenta>
            CuentaRepository.listaCuentas = it
            if (it.isNotEmpty()) _cuentaActual.value = it[0]

        }, {
            errorCuenta.value = it.message
        })
    }

    fun agregarCuenta(context: Context){
        CuentaRepository.createCuenta({
            val cuenta = Cuenta(it.id, it.numero, it.saldo, it.userId)
            CuentaRepository.listaCuentas?.add(cuenta)
            _listaCuentas.value = _listaCuentas.value
            Toast.makeText(context, "Cuenta creada con exito", Toast.LENGTH_SHORT).show()
        },{
            errorCuenta.value = it.message
        })
    }

    fun setCuentaActual (posicion: Int){
        if (posicion >= (listaCuentas.value?.size ?: 0) || posicion < 0){
            return
        }
       _cuentaActual.value = listaCuentas.value?.get(posicion)
    }

    fun fetchExtractos(){
        if (cuentaActual.value == null){
            return
        }
        CuentaRepository.fetchExtracto(cuentaActual.value!!.id, {
            _listaExtractos.value = it as ArrayList<Extracto>
        },
            {
                Log.e("CuentaDetailViewModel", "Error al obtener los extractos", it)
            })
    }

}