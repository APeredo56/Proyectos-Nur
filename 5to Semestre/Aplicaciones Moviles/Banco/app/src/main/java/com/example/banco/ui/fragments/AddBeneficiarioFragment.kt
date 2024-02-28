package com.example.banco.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.banco.databinding.FragmentAddBeneficiarioBinding
import com.example.banco.ui.viewModels.AddBeneficiarioViewModel


class AddBeneficiarioFragment : DialogFragment() {

    private lateinit var binding: FragmentAddBeneficiarioBinding
    private lateinit var viewModel: AddBeneficiarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(AddBeneficiarioViewModel::class.java)
        setupOnClickListeners()
        setupViewModelObservers()
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        binding = FragmentAddBeneficiarioBinding.inflate(inflater!!, null, false)
        builder.setView(binding.root);

        return builder.create()
    }

    private fun setupOnClickListeners() {
        binding.btnCreateBeneficiario.setOnClickListener {
            val nombre = binding.txtAddNombreBeneficiario.editText?.text.toString()
            val ci = binding.txtAddCIBeneficiario.editText?.text.toString()
            val cuenta = binding.txtAddCuentaBeneficiario.editText?.text.toString()
            if (viewModel.verifyData(nombre, ci, cuenta)) {
                viewModel.addBeneficiario(nombre, ci, cuenta, requireContext()) { dismiss() }
            }
        }

        binding.btnCancelarBeneficiario.setOnClickListener {
            dismiss()
        }
    }

    private fun setupViewModelObservers() {
        viewModel.addErrors.observe(viewLifecycleOwner) {
            showErrors(it)
        }
    }

    private fun showErrors(errors: ArrayList<AddBeneficiarioViewModel.AddBeneficiarioError>) {
        binding.txtAddCuentaBeneficiario.isErrorEnabled = false
        binding.txtAddCIBeneficiario.isErrorEnabled = false
        binding.txtAddNombreBeneficiario.isErrorEnabled = false

        for (error in errors) {
            when (error) {
                AddBeneficiarioViewModel.AddBeneficiarioError.EMPTY_NAME -> {
                    binding.txtAddNombreBeneficiario.error =
                        "El nombre no puede estar vacío"
                }

                AddBeneficiarioViewModel.AddBeneficiarioError.EMPTY_CI -> {
                    binding.txtAddCIBeneficiario.error = "La cédula no puede estar vacía"
                }

                AddBeneficiarioViewModel.AddBeneficiarioError.EMPTY_ACCOUNT_NUMBER -> {
                    binding.txtAddCuentaBeneficiario.error =
                        "El número de cuenta no puede estar vacío"
                }
            }
        }
    }
}