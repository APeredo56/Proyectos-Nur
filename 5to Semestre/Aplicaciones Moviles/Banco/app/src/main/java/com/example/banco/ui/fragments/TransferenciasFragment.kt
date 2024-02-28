package com.example.banco.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco.databinding.FragmentTransferenciasBinding
import com.example.banco.ui.adapters.BeneficiarioAdapter
import com.example.banco.ui.viewModels.TransferenciasViewModel

class TransferenciasFragment : Fragment(), BeneficiarioAdapter.OnBeneficiarioClickListener {

    private lateinit var binding: FragmentTransferenciasBinding
    private lateinit var viewModel: TransferenciasViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(TransferenciasViewModel::class.java)
        binding = FragmentTransferenciasBinding.inflate(inflater, container, false)

        setupRecyclerView()
        setupViewModelObservers()
        setupOnClickListeners()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchListaBeneficiarios(requireContext())
    }

    private fun setupRecyclerView() {
        val dividerItemDecoration = DividerItemDecoration(
            binding.rvBeneficiarios.context,
            DividerItemDecoration.VERTICAL
        )
        binding.rvBeneficiarios.apply {
            adapter = BeneficiarioAdapter(
                arrayListOf(),
                this@TransferenciasFragment
            )
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(dividerItemDecoration)
        }
    }


    private fun setupViewModelObservers() {
        viewModel.listaBeneficiarios.observe(viewLifecycleOwner) {
            val adapter = binding.rvBeneficiarios.adapter as BeneficiarioAdapter
            adapter.beneficiarios = it
            binding.rvBeneficiarios.adapter?.notifyDataSetChanged()
        }
    }

    private fun setupOnClickListeners() {
        binding.btnAddBeneficiario.setOnClickListener {
            val addBeneficiarioFragment = AddBeneficiarioFragment()
            addBeneficiarioFragment.show(parentFragmentManager, "addBeneficiarioDialog")
        }
    }

    override fun onBeneficiarioClick(position: Int) {
        val beneficiario = viewModel.listaBeneficiarios.value!!.get(position)
        val action =
            TransferenciasFragmentDirections.actionNavigationTransferenciasToHacerTransferenciaFragment(
                beneficiario.id)
        findNavController().navigate(action)
    }


}