package com.example.banco.ui.fragments

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.banco.api.model.Cuenta
import com.example.banco.api.repositories.BeneficiarioRepository
import com.example.banco.api.repositories.CuentaRepository
import com.example.banco.databinding.FragmentCuentasBinding
import com.example.banco.ui.activities.LoginActivity
import com.example.banco.ui.adapters.CuentasAdapter
import com.example.banco.ui.adapters.ExtractosAdapter
import com.example.banco.ui.viewModels.CuentasViewModel


class CuentasFragment() : Fragment() {

    private lateinit var binding: FragmentCuentasBinding
    private lateinit var viewModel: CuentasViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(CuentasViewModel::class.java)
        binding = FragmentCuentasBinding.inflate(inflater, container, false)

        setupRecyclerViews()
        setupViewModelObservers()
        setupEventListeners()
        viewModel.fetchCuentas()

        return binding.root
    }

    fun setupRecyclerViews() {

        binding.rvCuentas.apply {
            layoutManager =
                LinearLayoutManager(binding.rvCuentas.context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
            adapter = CuentasAdapter(arrayListOf())
            itemAnimator = DefaultItemAnimator()
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val posicion =
                            (layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                        viewModel.setCuentaActual(posicion)
                    }
                }
            })
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)

        }

        binding.rvExtractos.apply {
            val dividerItemDecoration = DividerItemDecoration(
                binding.rvExtractos.context,
                DividerItemDecoration.VERTICAL
            )
            layoutManager =
                LinearLayoutManager(binding.rvExtractos.context)
                    .apply {
                        orientation = LinearLayoutManager.VERTICAL
                    }
            (layoutManager as LinearLayoutManager).reverseLayout = true
            adapter = ExtractosAdapter(arrayListOf())
            addItemDecoration(dividerItemDecoration)
        }


    }

    fun setupEventListeners() {
        binding.btnAddCuenta.setOnClickListener {
            viewModel.agregarCuenta(requireContext())
        }

        binding.btnLogOut.setOnClickListener {
            requireActivity().getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE).edit()
                .clear().apply()
            CuentaRepository.listaCuentas = arrayListOf()
            BeneficiarioRepository.listaBeneficiarios = arrayListOf()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()

        }
    }

    fun setupViewModelObservers() {
        viewModel.listaCuentas.observe(viewLifecycleOwner) {
            val adapter = binding.rvCuentas.adapter as CuentasAdapter
            adapter.cuentas = viewModel.listaCuentas.value!!
            adapter.notifyDataSetChanged()
        }

        viewModel.errorCuenta.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                Toast.makeText(requireContext(), "Error, $it", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.cuentaActual.observe(viewLifecycleOwner) {
            viewModel.fetchExtractos()
        }

        viewModel.listaExtractos.observe(viewLifecycleOwner) {
            val adapter = binding.rvExtractos.adapter as ExtractosAdapter
            adapter.extractos = it
            adapter.notifyDataSetChanged()
            try {
                binding.rvExtractos.scrollToPosition(adapter.extractos.size - 1)
            } catch (_: Exception) {

            }

        }
    }


}