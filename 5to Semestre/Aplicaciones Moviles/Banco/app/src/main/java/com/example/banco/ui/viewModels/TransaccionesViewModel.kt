package com.example.banco.ui.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.banco.api.model.Cuenta
import com.example.banco.api.model.ErrorTransaccion
import com.example.banco.api.repositories.CuentaRepository

class TransaccionesViewModel : ViewModel() {

    private var _ingreso = MutableLiveData(false)
    private var _cuenta = MutableLiveData<Cuenta>()
    private var _listaCuentas = MutableLiveData<ArrayList<Cuenta>>()
    private var _listaErrores = MutableLiveData<ArrayList<ErrorTransaccion>>(arrayListOf())

    val ingreso get() = _ingreso
    val cuenta get() = _cuenta
    val listaCuentas get() = _listaCuentas
    val listaErrores get() = _listaErrores

    fun setIngreso(ingreso: Boolean) {
        _ingreso.value = ingreso
    }

    fun cargarCuentas() {
        _listaCuentas.value = CuentaRepository.listaCuentas
        if (cuenta.value == null && (listaCuentas.value?.size ?: 0) > 0) {
            _cuenta.value = listaCuentas.value?.get(0)
        }
    }

    fun setCuenta(posicion: Int) {
        if (posicion >= (listaCuentas.value?.size ?: 0) || posicion < 0) {
            return
        }
        _cuenta.value = listaCuentas.value?.get(posicion)
    }

    fun validarTransaccion(monto: Double, descripcion: String): Boolean {
        _listaErrores.value = arrayListOf()
        if (ingreso.value == false && (cuenta.value?.saldo ?: 0.0) < monto) {
            _listaErrores.value?.add(ErrorTransaccion.NOT_ENOUGH_MONEY)
        }
        if (monto <= 0.0) {
            _listaErrores.value?.add(ErrorTransaccion.EMPTY_AMOUNT)
        }

        if (descripcion.isEmpty()) {
            _listaErrores.value?.add(ErrorTransaccion.EMPTY_DESCRIPTION)
        }
        return if (listaErrores.value!!.isEmpty()) {
            true
        } else {
            _listaErrores.value = _listaErrores.value
            false
        }
    }

    fun realizarTransaccion(
        monto: Double,
        descripcion: String,
        context: Context,
        navController: NavController
    ) {
        if (ingreso.value == true) {
            CuentaRepository.registrarIngreso(cuenta.value?.id ?: 0, descripcion, monto,
                {
                    Toast.makeText(context, "Transacción realizada con éxito", Toast.LENGTH_SHORT)
                        .show()
                    cuenta.value?.saldo = cuenta.value?.saldo?.plus(monto)!!
                    navController.navigateUp()
                }, {
                    Toast.makeText(
                        context,
                        "Error al realizar la transacción, ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                })
        } else {
            CuentaRepository.registrarEgreso(cuenta.value?.id ?: 0, descripcion, monto,
                {
                    Toast.makeText(context, "Transacción realizada con éxito", Toast.LENGTH_SHORT)
                        .show()
                    cuenta.value?.saldo = cuenta.value?.saldo?.minus(monto)!!
                    navController.navigateUp()
                }, {
                    Toast.makeText(
                        context,
                        "Error al realizar la transacción, ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                })
        }
    }

}