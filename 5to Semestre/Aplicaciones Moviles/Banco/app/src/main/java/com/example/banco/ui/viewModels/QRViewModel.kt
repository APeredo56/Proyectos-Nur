package com.example.banco.ui.viewModels

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.banco.api.model.Cuenta
import com.example.banco.api.model.ErrorTransaccion
import com.example.banco.api.model.PagoQRModel
import com.example.banco.api.model.QRModel
import com.example.banco.api.repositories.CuentaRepository
import com.example.banco.api.repositories.QRRepository
import com.example.banco.ui.fragments.QrPopUpFragment

class QRViewModel : ViewModel() {

    private var _listaCuentas = MutableLiveData<ArrayList<Cuenta>>()
    private var _cuentaSeleccionada = MutableLiveData<Cuenta>()
    private var _listaErrores = MutableLiveData<ArrayList<ErrorTransaccion>>(arrayListOf())


    val listaCuentas get() = _listaCuentas
    val cuentaSeleccionada get() = _cuentaSeleccionada
    val listaErrores get() = _listaErrores
    val codigoQR = MutableLiveData<String>()


    fun cambiarCuentaSeleccionada(posicion: Int) {
        if (posicion >= (listaCuentas.value?.size ?: 0) || posicion < 0) {
            return
        }
        _cuentaSeleccionada.value = listaCuentas.value?.get(posicion)
    }

    fun cargarCuentas() {
        _listaCuentas.value = CuentaRepository.listaCuentas
        if (cuentaSeleccionada.value == null && (listaCuentas.value?.size ?: 0) > 0) {
            _cuentaSeleccionada.value = listaCuentas.value?.get(0)
        }
    }

    fun validarTransaccion(monto: Double, fecha: String, pago: Boolean): Boolean {
        if (monto <= 0.0 && !pago) {
            _listaErrores.value?.add(ErrorTransaccion.EMPTY_AMOUNT)
        }

        if (monto > (cuentaSeleccionada.value?.saldo ?: 0.0) && pago) {
            _listaErrores.value?.add(ErrorTransaccion.NOT_ENOUGH_MONEY)
        }

        if (fecha.isEmpty() && !pago) {
            _listaErrores.value?.add(ErrorTransaccion.EMPTY_DATE)
        }
        return if (listaErrores.value!!.isEmpty()) {
            true
        } else {
            _listaErrores.value = _listaErrores.value
            Log.e("listaErrores", listaErrores.value.toString())
            false
        }
    }

    fun crearQR(monto: String, fecha: String, context: Context, fragmentManager: FragmentManager) {
        val qr = QRModel(monto,cuentaSeleccionada.value?.id ?: 0, fecha)
        QRRepository.crearQR(qr, {
            val qrPopUpFragment = QrPopUpFragment(it.imagen, context.applicationContext)
            qrPopUpFragment.show(fragmentManager, "QR Pop Up")
        },{
            Toast.makeText(context, "Error al crear QR", Toast.LENGTH_SHORT).show()
        })

    }

    fun pagarQR(context: Context, navController: NavController){
        val qr = codigoQR.value?.let { PagoQRModel(it, (cuentaSeleccionada.value?.id ?: 0).toString()) }
        Log.e("QR", qr.toString())
        if (qr != null) {
            QRRepository.pagarQR(qr, {
                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder.setTitle("Pago Exitoso")
                builder.setMessage("El pago se realizo con exito")
                builder.setPositiveButton("OK"
                ) { dialogInterface, i -> dialogInterface.dismiss() }
                    .show()
                navController.navigateUp()
            },{
                Toast.makeText(context, "Error al pagar QR", Toast.LENGTH_SHORT).show()
            })
        } else {
            Toast.makeText(context, "Error, no se escaneo ningun codigo", Toast.LENGTH_SHORT).show()
        }
    }




}