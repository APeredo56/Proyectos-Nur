package com.example.banco.ui.viewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.banco.api.model.BeneficiarioResponse
import com.example.banco.api.repositories.BeneficiarioRepository

class TransferenciasViewModel : ViewModel() {

    private val _listaBeneficiarios = MutableLiveData<ArrayList<BeneficiarioResponse>>()


    val listaBeneficiarios get() = _listaBeneficiarios

    fun fetchListaBeneficiarios(context: Context) {
        if (BeneficiarioRepository.getBeneficiarios() != null) {
            _listaBeneficiarios.value = BeneficiarioRepository.getBeneficiarios()
            return
        }
        BeneficiarioRepository.fetchBeneficiarios(
            {
                _listaBeneficiarios.value = it as ArrayList<BeneficiarioResponse>
            },
            {
                Toast.makeText(
                    context,
                    "Ocurrio un error al cargar la lista de beneficiarios",
                    Toast.LENGTH_SHORT
                ).show()
            })
    }
}