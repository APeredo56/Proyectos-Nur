package com.example.banco.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banco.R
import com.example.banco.api.model.BeneficiarioResponse

class BeneficiarioAdapter(
    var beneficiarios: ArrayList<BeneficiarioResponse>,
    val listener: OnBeneficiarioClickListener
) :
    RecyclerView.Adapter<BeneficiarioAdapter.BeneficiarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeneficiarioViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_beneficiario, parent, false)
        return BeneficiarioViewHolder(view)
    }

    override fun getItemCount(): Int {
        return beneficiarios.size
    }

    override fun onBindViewHolder(holder: BeneficiarioViewHolder, position: Int) {
        val beneficiario = beneficiarios[position]
        holder.lblNroCuentaBeneficiario.text = beneficiario.numeroCuenta
        holder.lblNombreBeneficiario.text = beneficiario.nombreCompleto
        holder.itemView.setOnClickListener { listener.onBeneficiarioClick(position)}
    }

    class BeneficiarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblNroCuentaBeneficiario: TextView = itemView.findViewById(R.id.lblNroCuentaBeneficiario)
        val lblNombreBeneficiario: TextView = itemView.findViewById(R.id.lblNombreBeneficiario)
    }

    interface OnBeneficiarioClickListener {
        fun onBeneficiarioClick(position: Int)
    }
}