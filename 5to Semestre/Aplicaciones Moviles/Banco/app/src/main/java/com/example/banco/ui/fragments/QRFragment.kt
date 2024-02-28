package com.example.banco.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
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
import com.example.banco.databinding.FragmentQrBinding
import com.example.banco.ui.adapters.SelectorCuentaAdapter
import com.example.banco.ui.viewModels.QRViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import java.text.SimpleDateFormat


class QRFragment : Fragment() {

    private lateinit var viewModel: QRViewModel
    private lateinit var binding: FragmentQrBinding
    private var pago = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQrBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(QRViewModel::class.java)


        setupRecyclerView()
        setupViewModelObservers()
        setupOnClickListeners()

        pago = arguments?.getBoolean("pago", false) == true

        setupUI()
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
        binding.rvSelectCuentaQr.apply {
            layoutManager = LinearLayoutManager(binding.rvSelectCuentaQr.context).apply {
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
                        val adapter = binding.rvSelectCuentaQr.adapter as SelectorCuentaAdapter
                        val oldPos = adapter.selectedPos
                        adapter.selectedPos = posicion
                        adapter.notifyItemChanged(oldPos)
                        adapter.notifyItemChanged(posicion)
                        viewModel.cambiarCuentaSeleccionada(posicion)
                    }
                }
            })
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.listaCuentas.observe(viewLifecycleOwner) {
            val adapter = binding.rvSelectCuentaQr.adapter as SelectorCuentaAdapter
            adapter.cuentas = it
            adapter.notifyDataSetChanged()
        }

        viewModel.listaErrores.observe(viewLifecycleOwner) {
            mostrarErrores(it)
        }


    }

    private fun setupOnClickListeners() {
        binding.btnCancelarQr.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnAceptarQr.setOnClickListener {
            var monto = -1.0
            if (binding.txtMontoQr.editText?.text.toString().isNotEmpty()) {
                monto = binding.txtMontoQr.editText?.text.toString().toDouble()
            }
            if (!viewModel.validarTransaccion(monto, binding.txtFechaQr.editText?.text.toString(), pago)) {
                return@setOnClickListener
            }
            if (!pago) {
                viewModel.crearQR(monto.toString(), binding.txtFechaQr.editText?.text.toString(),
                    requireContext(), parentFragmentManager)
            }else{
                viewModel.pagarQR(requireContext(), findNavController())
            }

        }

        binding.txtFechaQr.setStartIconOnClickListener {
            val materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Fecha de nacimiento")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            materialDatePicker.addOnPositiveButtonClickListener {
                val date = SimpleDateFormat("yyyy/MM/dd")
                binding.txtFechaQr.editText?.setText(date.format(it))
            }
            materialDatePicker.show(parentFragmentManager, "DATE_PICKER")
        }

        binding.BtnEscaner.setOnClickListener {
            escanearQR()
        }
    }

    private fun setupUI(){
        if(pago){
            binding.txtFechaQr.visibility = View.GONE
            binding.txtMontoQr.visibility = View.GONE
            binding.lblCuentaQR.visibility = View.GONE
            binding.rvSelectCuentaQr.visibility = View.GONE
            binding.submitQRContainer.visibility = View.GONE
        } else{
            binding.BtnEscaner.visibility = View.GONE
        }
    }



    private fun mostrarErrores(errores: ArrayList<ErrorTransaccion>) {
        binding.txtMontoQr.error = null
        binding.txtFechaQr.error = null

        errores.forEach {
            when (it) {
                ErrorTransaccion.EMPTY_AMOUNT -> binding.txtMontoQr.error =
                    "Este campo es obligatorio"

                ErrorTransaccion.EMPTY_DATE -> binding.txtFechaQr.error =
                    "Este campo es obligatorio"

                ErrorTransaccion.NOT_ENOUGH_MONEY -> binding.txtMontoQr.error =
                    "El monto ingresado es mayor al saldo de la cuenta"

                else -> {}
            }
        }

    }

    private fun escanearQR(){
        val scanner = ScanOptions()
        scanner.setPrompt("Escanea el QR")
        scanner.setOrientationLocked(false)
        scanner.setBeepEnabled(false)
        scanner.captureActivity = CaptureActivity::class.java
        barLaucher.launch(scanner)

    }

    var barLaucher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents != null) {
            binding.lblCuentaQR.visibility = View.VISIBLE
            binding.rvSelectCuentaQr.visibility = View.VISIBLE
            binding.submitQRContainer.visibility = View.VISIBLE
            viewModel.codigoQR.value = result.contents
        } else{
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
            builder.setTitle("Ocurrio un Error")
            builder.setMessage("No se pudo escanear el QR")
            builder.setPositiveButton("OK"
            ) { dialogInterface, i -> dialogInterface.dismiss() }
                .show()
        }
    }


}