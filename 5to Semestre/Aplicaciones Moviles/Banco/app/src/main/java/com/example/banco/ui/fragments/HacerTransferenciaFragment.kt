package com.example.banco.ui.fragments

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.banco.api.model.ErrorTransaccion
import com.example.banco.databinding.FragmentHacerTransferenciaBinding
import com.example.banco.ui.adapters.SelectorCuentaAdapter
import com.example.banco.ui.viewModels.HacerTransferenciaViewModel

class HacerTransferenciaFragment : Fragment() {

    private lateinit var binding: FragmentHacerTransferenciaBinding
    private lateinit var viewModel: HacerTransferenciaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHacerTransferenciaBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(HacerTransferenciaViewModel::class.java)

        setupRecyclerView()
        setupViewModelObservers()
        setupOnClickListener()

        binding.txtDescripcionTransferencia.editText?.inputType = InputType.TYPE_CLASS_TEXT or
                InputType.TYPE_TEXT_FLAG_MULTI_LINE
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigateUp()
                }
            }
            )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.cargarCuentas()
    }

    private fun setupRecyclerView() {
        binding.rvSelectCuentaTransferencia.apply {
            layoutManager = LinearLayoutManager(binding.rvSelectCuentaTransferencia.context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = SelectorCuentaAdapter(
                arrayListOf()
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val posicion =
                            (layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                        val adapter = binding.rvSelectCuentaTransferencia.adapter as SelectorCuentaAdapter
                        val oldPos = adapter.selectedPos
                        adapter.selectedPos = posicion
                        adapter.notifyItemChanged(oldPos)
                        adapter.notifyItemChanged(posicion)
                        viewModel.setCuenta(posicion)
                    }
                }
            })
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.listaCuentas.observe(viewLifecycleOwner) {
            (binding.rvSelectCuentaTransferencia.adapter as SelectorCuentaAdapter).cuentas = it
        }
        viewModel.listaErrores.observe(viewLifecycleOwner) {
            mostrarErrores()
        }
    }

    private fun setupOnClickListener() {
        binding.btnTransferir.setOnClickListener {
            var monto = -1.0
            binding.txtMontoTransferencia.editText?.text.let {
                if (it!!.isNotEmpty()) {
                    monto = it.toString().toDouble()
                }
            }
            val descripcion = binding.txtDescripcionTransferencia.editText?.text.toString()
            if (viewModel.validarTransaccion(monto, descripcion)) {
                val idBeneficiario = arguments?.getInt("idBeneficiario")
                val navController = findNavController()
                viewModel.transferir(
                    descripcion,
                    monto,
                    idBeneficiario!!,
                    requireContext(),
                    navController
                )
            }
        }

        binding.btnCancelarTransferencia.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun mostrarErrores() {
        binding.txtMontoTransferencia.error = null
        binding.txtDescripcionTransferencia.error = null
        for (error in viewModel.listaErrores.value!!) {
            when (error) {
                ErrorTransaccion.NOT_ENOUGH_MONEY -> binding.txtMontoTransferencia.error =
                    "El monto ingresado es mayor al saldo de la cuenta"

                ErrorTransaccion.EMPTY_AMOUNT ->
                    binding.txtMontoTransferencia.error = "Este campo no puede estar vacio"

                ErrorTransaccion.EMPTY_DESCRIPTION -> binding.txtDescripcionTransferencia.error =
                    "Este campo no puede estar vacio"

                else -> {}
            }
        }
    }


}