package com.example.banco.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.banco.databinding.FragmentPagosBinding

class PagosFragment : Fragment() {

    private lateinit var binding: FragmentPagosBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagosBinding.inflate(inflater, container, false)

        setupClickListeners()
        return binding.root
    }

    private fun setupClickListeners() {
        binding.btnIngreso.setOnClickListener {
            val action = PagosFragmentDirections.actionNavigationPagosToTransaccionesFragment(true)
            findNavController().navigate(action)
        }

        binding.btnRetiro.setOnClickListener {
            val action = PagosFragmentDirections.actionNavigationPagosToTransaccionesFragment(false)
            findNavController().navigate(action)
        }

        binding.btnCrearQr.setOnClickListener {
            val action = PagosFragmentDirections.actionNavigationPagosToCrearQRFragment(false)
            findNavController().navigate(action)
        }

        binding.btnPagoQr.setOnClickListener {
            val action = PagosFragmentDirections.actionNavigationPagosToCrearQRFragment(true)
            findNavController().navigate(action)
        }

    }
}