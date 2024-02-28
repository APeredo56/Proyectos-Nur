package com.example.banco.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.banco.api.model.ErrorTransaccion
import com.example.banco.databinding.FragmentTransaccionesBinding
import com.example.banco.ui.adapters.SelectorCuentaAdapter
import com.example.banco.ui.viewModels.TransaccionesViewModel

class TransaccionesFragment : Fragment() {

    private lateinit var viewModel: TransaccionesViewModel
    private lateinit var binding: FragmentTransaccionesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(TransaccionesViewModel::class.java)
        binding = FragmentTransaccionesBinding.inflate(inflater, container, false)

        binding.txtDescripcion.editText?.inputType = InputType.TYPE_CLASS_TEXT or
                InputType.TYPE_TEXT_FLAG_MULTI_LINE
        setupRecyclerView()
        setupViewModelObservers()
        setupOnClickListeners()
        viewModel.cargarCuentas()

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
        binding.rvSelectCuenta.apply {
            layoutManager = LinearLayoutManager(binding.rvSelectCuenta.context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = SelectorCuentaAdapter(
                arrayListOf()
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val posicion = (layoutManager as LinearLayoutManager).
                        findFirstCompletelyVisibleItemPosition()
                        val adapter = binding.rvSelectCuenta.adapter as SelectorCuentaAdapter
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
        arguments?.getBoolean("ingreso").let {
            if (it != null) {
                viewModel.setIngreso(it)
            }
        }

        viewModel.listaCuentas.observe(viewLifecycleOwner) {
            (binding.rvSelectCuenta.adapter as SelectorCuentaAdapter).cuentas = it
        }

        viewModel.listaErrores.observe(viewLifecycleOwner) {
            mostrarErrores()
        }

    }

    private fun setupOnClickListeners() {
        binding.btnCancelarTransaccion.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnAceptarTransaccion.setOnClickListener {
            var monto = -1.0
            binding.txtMonto.editText?.text?.let {
                if (it.isNotEmpty()) {
                    monto = it.toString().toDouble()
                }
            }
            val descripcion = binding.txtDescripcion.editText?.text.toString()
            if (viewModel.validarTransaccion(monto, descripcion)) {
                activity?.let { viewModel.realizarTransaccion(monto,
                    descripcion, it, findNavController()) }
            }
        }
    }


    private fun mostrarErrores() {
        binding.txtMonto.isErrorEnabled = false
        binding.txtDescripcion.isErrorEnabled = false
        viewModel.listaErrores.value?.forEach {
            when (it) {
                ErrorTransaccion.NOT_ENOUGH_MONEY -> {
                    binding.txtMonto.error = "El monto ingresado es mayor al saldo de la cuenta"
                }
                ErrorTransaccion.EMPTY_DESCRIPTION -> {
                    binding.txtDescripcion.error = "Este campo no puede estar vacio"
                }

                ErrorTransaccion.EMPTY_AMOUNT -> {
                    binding.txtMonto.error = "Este campo no puede estar vacio"
                }

                else -> {}
            }
        }
    }



}