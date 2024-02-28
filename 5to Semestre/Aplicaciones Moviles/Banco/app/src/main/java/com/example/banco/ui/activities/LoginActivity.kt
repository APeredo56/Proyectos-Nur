package com.example.banco.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.banco.R
import com.example.banco.api.services.AuthInterceptor
import com.example.banco.databinding.ActivityLoginBinding
import com.example.banco.ui.viewModels.LoginViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat


class LoginActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setContentView(binding.root)

        setupEventListeners()
        setupViewModelObservers()

    }

    private fun setupEventListeners(){
        binding.lblLogin.setOnClickListener {
            viewModel.switchIsLogginIn()
        }

        binding.lblRegister.setOnClickListener {
            viewModel.switchIsLogginIn()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.txtEmailLogin.editText?.text.toString()
            val password = binding.txtPasswordLogin.editText?.text.toString()
            viewModel.updateValues("","",email, password,  "", "", "")
            if (viewModel.validarFormulario()){
                viewModel.login(this)
            } else {
                showErrorsInInputs()
            }
        }

        binding.btnRegister.setOnClickListener {
            val nombre = binding.txtName.editText?.text.toString()
            val apellidos = binding.txtLastname.editText?.text.toString()
            val email = binding.txtEmailRegister.editText?.text.toString()
            val password = binding.txtPasswordRegister.editText?.text.toString()
            val passwordConfirmation = binding.txtConfirmPassword.editText?.text.toString()
            val fechaNacimiento = binding.txtBirthDate.editText?.text.toString()
            val ci = binding.txtCI.editText?.text.toString()

            viewModel.updateValues(nombre, apellidos, email, password, passwordConfirmation,
                fechaNacimiento, ci)

            if(viewModel.validarFormulario()){
                viewModel.register(this)
            }
        }

        binding.txtBirthDate.setStartIconOnClickListener {
            val materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Fecha de nacimiento")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            materialDatePicker.addOnPositiveButtonClickListener {
                val date = SimpleDateFormat("yyyy/MM/dd")
                binding.txtBirthDate.editText?.setText(date.format(it))
            }
            materialDatePicker.show(supportFragmentManager, "DATE_PICKER")
        }
    }

    private fun setupViewModelObservers(){
        viewModel.isLogginIn.observe(this) {
            if (it) {
                binding.formLogin.visibility = View.VISIBLE
                binding.formRegister.visibility = View.GONE
                binding.btnRegister.visibility = View.GONE
                binding.lblLogin.background = getDrawable(R.drawable.red_gradient)
                binding.lblLogin.setTextColor(getColor(R.color.white))
                binding.lblRegister.background = getDrawable(R.drawable.gray_border_bg)
                binding.lblRegister.setTextColor(getColor(R.color.red))
            } else {
                binding.formLogin.visibility = View.GONE
                binding.formRegister.visibility = View.VISIBLE
                binding.btnRegister.visibility = View.VISIBLE
                binding.lblRegister.background = getDrawable(R.drawable.red_gradient)
                binding.lblRegister.setTextColor(getColor(R.color.white))
                binding.lblLogin.background = getDrawable(R.drawable.gray_border_bg)
                binding.lblLogin.setTextColor(getColor(R.color.red))
            }
        }

        viewModel.formErrors.observe(this) {
            showErrorsInInputs()
        }

        viewModel.loginSuccess.observe(this){
            if (it){
//                PreferenceManager.getDefaultSharedPreferences(this).edit()
//                    .putString("token", loginResponse.access_token).apply()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        viewModel.registerSuccess.observe(this){
            if (it){
                Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loginErrorMsg.observe(this){
            if (it != null){
                if (viewModel.loginErrorMsg.value == "Datos incorrectos") {
                    Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Ocurrio un error inesperado, intente de nuevo más tarde",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.registerErrorMsg.observe(this){
            if (it != null){
                if (viewModel.registerErrorMsg.value == "El email ya está en uso") {
                    binding.txtEmailRegister.error = "El email ya está en uso"
                } else {
                    Toast.makeText(this, "Ocurrio un error inesperado, intente de nuevo más tarde",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showErrorsInInputs(){
        binding.txtName.isErrorEnabled = false
        binding.txtLastname.isErrorEnabled = false
        binding.txtEmailRegister.isErrorEnabled = false
        binding.txtEmailLogin.isErrorEnabled = false
        binding.txtPasswordRegister.isErrorEnabled = false
        binding.txtPasswordLogin.isErrorEnabled = false
        binding.txtConfirmPassword.isErrorEnabled = false
        binding.txtCI.isErrorEnabled = false
        binding.txtBirthDate.isErrorEnabled = false

        if (viewModel.formErrors.value!!.isNullOrEmpty()) return

        for (error in viewModel.formErrors.value!!){
            when(error) {
                LoginViewModel.FormErrors.MISSING_NAME -> binding.txtName.error =
                    "El nombre es requerido"
                LoginViewModel.FormErrors.MISSING_LASTNAME -> binding.txtLastname.error =
                    "El apellido es requerido"
                LoginViewModel.FormErrors.MISSING_EMAIL -> {
                    if (!viewModel.isLogginIn.value!!){
                        binding.txtEmailRegister.error = "El email es requerido"
                    } else {
                        binding.txtEmailLogin.error = "El email es requerido"
                    }
                }
                LoginViewModel.FormErrors.MISSING_PASSWORD -> {
                    if (!viewModel.isLogginIn.value!!){
                        binding.txtPasswordRegister.error = "La contraseña es requerida"
                    } else {
                        binding.txtPasswordLogin.error = "La contraseña es requerida"
                    }
                }
                LoginViewModel.FormErrors.MISSING_PASSWORD_CONFIRMATION -> binding.txtConfirmPassword.error =
                    "La confirmación de contraseña es requerida"
                LoginViewModel.FormErrors.MISSING_CI -> binding.txtCI.error =
                    "El CI es requerido"
                LoginViewModel.FormErrors.MISSING_BIRTHDATE -> binding.txtBirthDate.error =
                    "La fecha de nacimiento es requerida"
                LoginViewModel.FormErrors.PASSWORDS_NOT_MATCHING -> binding.txtConfirmPassword.error =
                    "Las contraseñas no coinciden"
                LoginViewModel.FormErrors.INVALID_PASSWORD -> {
                    if (!viewModel.isLogginIn.value!!){
                        binding.txtPasswordRegister.error = "La contraseña debe tener al menos 8 caracteres"
                    } else {
                        binding.txtPasswordLogin.error = "La contraseña debe tener al menos 8 caracteres"
                    }
                }
            }

        }
    }





}