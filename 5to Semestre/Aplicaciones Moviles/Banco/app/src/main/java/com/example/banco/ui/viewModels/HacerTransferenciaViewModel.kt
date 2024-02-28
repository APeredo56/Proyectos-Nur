package com.example.banco.ui.viewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.banco.api.model.Cuenta
import com.example.banco.api.model.ErrorTransaccion
import com.example.banco.api.model.Transferencia
import com.example.banco.api.repositories.BeneficiarioRepository
import com.example.banco.api.repositories.CuentaRepository
import com.example.banco.api.repositories.TransferenciaRepository

class HacerTransferenciaViewModel : ViewModel() {
    private var _listaCuentas = MutableLiveData<ArrayList<Cuenta>>(arrayListOf())
    private var _listaErrores = MutableLiveData<ArrayList<ErrorTransaccion>>(arrayListOf())


    private var cuenta: Cuenta? = null

    val listaCuentas get() = _listaCuentas
    val listaErrores get() = _listaErrores

    fun setCuenta(posicion: Int) {
        if (posicion >= (listaCuentas.value?.size ?: 0) || posicion < 0) {
            return
        }
        cuenta = listaCuentas.value?.get(posicion)
    }

    fun cargarCuentas() {
        _listaCuentas.value = CuentaRepository.listaCuentas
        cuenta = listaCuentas.value?.get(0)
    }

    fun validarTransaccion(monto: Double, descripcion: String): Boolean {
        _listaErrores.value = arrayListOf()
        if ((cuenta?.saldo ?: 0.0) < monto) {
            _listaErrores.value?.add(ErrorTransaccion.NOT_ENOUGH_MONEY)
        }
        if (monto <= 0.0) {
            _listaErrores.value?.add(ErrorTransaccion.EMPTY_AMOUNT)
        }
        if (descripcion.isEmpty()) {
            _listaErrores.value?.add(ErrorTransaccion.EMPTY_DESCRIPTION)
        }
        _listaErrores.value = _listaErrores.value
        return (_listaErrores.value?.size ?: 0) <= 0
    }

    fun transferir(
        descripcion: String,
        monto: Double,
        beneficiario: Int,
        context: Context,
        navController: NavController
    ) {
        val cuentaOrigen = cuenta!!.id
        val transferencia = Transferencia(descripcion, monto, cuentaOrigen.toString(), beneficiario)
        BeneficiarioRepository.fetchBeneficiarios({
            for (beneficiario in it) {
                Log.e("Lista de beneficiarios", "Lista de beneficiarios-> " + beneficiario.toString())
            }
                                                  }, {})
        Log.e("Cuenta origen", "Cuenta Origen-> " + cuenta.toString())
        Log.e("Transferencia", "Transferencia-> " + transferencia.toString())
        TransferenciaRepository.realizarTransferencia(transferencia, {
            Toast.makeText(context, "Transferencia realizada", Toast.LENGTH_SHORT).show()
            navController.navigateUp()
        }, {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        })
    }
}