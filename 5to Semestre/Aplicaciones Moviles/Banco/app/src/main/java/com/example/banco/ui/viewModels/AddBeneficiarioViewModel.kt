package com.example.banco.ui.viewModels

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.banco.api.model.Beneficiario
import com.example.banco.api.repositories.BeneficiarioRepository

class AddBeneficiarioViewModel : ViewModel() {
    private var _addErrors = MutableLiveData<ArrayList<AddBeneficiarioError>>(arrayListOf())

    val addErrors get() = _addErrors

    fun verifyData(nombre: String, ci: String, accountNumber: String): Boolean {
        _addErrors.value?.clear()

        if (nombre.isNullOrEmpty()) {
            _addErrors.value?.add(AddBeneficiarioError.EMPTY_NAME)
        }

        if (ci.isNullOrEmpty()) {
            _addErrors.value?.add(AddBeneficiarioError.EMPTY_CI)
        }

        if (accountNumber.isNullOrEmpty()) {
            _addErrors.value?.add(AddBeneficiarioError.EMPTY_ACCOUNT_NUMBER)
        }
        return if(_addErrors.value?.isEmpty() == true) {
            true
        } else {
            _addErrors.value = _addErrors.value
            false
        }
    }

    fun addBeneficiario(
        nombre: String,
        ci: String,
        accountNumber: String,
        context: Context,
        dismiss: () -> Unit
    ) {
        BeneficiarioRepository.insertBeneficiario(Beneficiario(accountNumber, ci, nombre), {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Agregado con exito")
                .setCancelable(false)
                .setPositiveButton("Ok") { dialog, id -> dismiss() }
            val alert = builder.create()
            alert.show()
        }, {

        })
    }


    enum class AddBeneficiarioError {
        EMPTY_NAME,
        EMPTY_CI,
        EMPTY_ACCOUNT_NUMBER,
    }
}