package com.example.banco.ui.viewModels

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.banco.api.model.RegisterModel
import com.example.banco.api.repositories.AutenticacionRepository
import com.example.banco.api.services.AuthInterceptor

class LoginViewModel : ViewModel() {

    private var _isLogginIn = MutableLiveData(true)
    private var _formErrors = MutableLiveData<ArrayList<FormErrors>>(arrayListOf())
    private var _loginSuccess = MutableLiveData(false)
    private var _registerSuccess = MutableLiveData(false)

    val name: MutableLiveData<String> = MutableLiveData()
    val lastName: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val passwordConfirmation: MutableLiveData<String> = MutableLiveData()
    val ci: MutableLiveData<String> = MutableLiveData()
    val birthdate: MutableLiveData<String> = MutableLiveData()

    val isLogginIn get() = _isLogginIn
    val formErrors get() = _formErrors
    val loginSuccess get() = _loginSuccess
    val registerSuccess get() = _registerSuccess

    val loginErrorMsg = MutableLiveData<String>()
    val registerErrorMsg = MutableLiveData<String>()

    fun updateValues(nombre: String, apellidos: String, email: String, password: String,
                     passwordConfirmation: String, birthDate: String, ci: String) {
        this.name.value = nombre
        this.lastName.value = apellidos
        this.email.value = email
        this.password.value = password
        this.passwordConfirmation.value = passwordConfirmation
        this.birthdate.value = birthDate
        this.ci.value = ci
    }

    fun validarFormulario() : Boolean{
        _formErrors.value?.clear()

        if (email.value.isNullOrEmpty()) {
            _formErrors.value?.add(FormErrors.MISSING_EMAIL)
        }

        if (password.value.isNullOrEmpty()) {
            _formErrors.value?.add(FormErrors.MISSING_PASSWORD)
        } else if (password.value!!.length < 8) {
            //_formErrors.value?.add(FormErrors.INVALID_PASSWORD)
        }

        if (isLogginIn.value!!){
            return _formErrors.value?.isEmpty() ?: false

        }

        if (name.value.isNullOrEmpty()) {
            _formErrors.value?.add(FormErrors.MISSING_NAME)
        }

        if (lastName.value.isNullOrEmpty()) {
            _formErrors.value?.add(FormErrors.MISSING_LASTNAME)
        }

        if (passwordConfirmation.value.isNullOrEmpty()) {
            _formErrors.value?.add(FormErrors.MISSING_PASSWORD_CONFIRMATION)
        } else if (passwordConfirmation.value != password.value) {
            _formErrors.value?.add(FormErrors.PASSWORDS_NOT_MATCHING)
        }

        if (ci.value.isNullOrEmpty()) {
            _formErrors.value?.add(FormErrors.MISSING_CI)
        }

        if (birthdate.value.isNullOrEmpty()) {
            _formErrors.value?.add(FormErrors.MISSING_BIRTHDATE)
        }
        if(_formErrors.value?.isEmpty() == true){
            return true
        } else {
            _formErrors.value = _formErrors.value
            return false
        }
    }

    fun switchIsLogginIn() {
        _isLogginIn.value = !_isLogginIn.value!!
    }

    fun login(context: Context){
        AutenticacionRepository.login(email.value!!, password.value!!, {
            _loginSuccess.value = true
            val token = it.access_token
            val sharedPref = context.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("token", token)
               apply()
            }
            AuthInterceptor.setToken(token)
        }, {
            loginErrorMsg.value = it.message
        })
    }

    fun register(context: Context){
        val nombreCompleto = "${name.value} ${lastName.value}"
        val model = RegisterModel(
            nombreCompleto,
            email.value!!,
            password.value!!,
            ci.value!!,
            birthdate.value!!
        )
        AutenticacionRepository.register(model, {
            _registerSuccess.value = true
            login(context)
        },{
            registerErrorMsg.value = it.message
        })
    }


    enum class FormErrors {
        MISSING_NAME,
        INVALID_PASSWORD,
        PASSWORDS_NOT_MATCHING,
        MISSING_LASTNAME,
        MISSING_EMAIL,
        MISSING_PASSWORD,
        MISSING_PASSWORD_CONFIRMATION,
        MISSING_CI,
        MISSING_BIRTHDATE,
    }

}