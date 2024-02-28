package com.example.banco.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banco.R
import com.example.banco.api.model.Cuenta

class CuentasAdapter (var cuentas: ArrayList<Cuenta>):
    RecyclerView.Adapter<CuentasAdapter.CuentaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuentaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_cuenta, parent, false)
        return CuentaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cuentas.size
    }

    override fun onBindViewHolder(holder: CuentaViewHolder, position: Int) {
        val cuenta = cuentas[position]
        holder.lblNroCuenta.text = cuenta.numero
        holder.lblSaldoCuenta.text = cuenta.saldo.toString() + " Bs"

    }

    class CuentaViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var lblNroCuenta = itemView.findViewById<TextView>(R.id.lblNroCuenta)
        var lblSaldoCuenta = itemView.findViewById<TextView>(R.id.lblSaldoCuenta)
    }

}