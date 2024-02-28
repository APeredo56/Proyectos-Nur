package com.example.banco.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banco.R
import com.example.banco.api.model.Cuenta

class SelectorCuentaAdapter(var cuentas: ArrayList<Cuenta>) :
    RecyclerView.Adapter<SelectorCuentaAdapter.SelectorCuentaViewHolder>() {
    var selectedPos = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectorCuentaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_selector_cuenta, parent, false)
        return SelectorCuentaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cuentas.size
    }

    override fun onBindViewHolder(holder: SelectorCuentaViewHolder, position: Int) {
        val cuenta = cuentas[position]
        holder.lblCuentaItem.text = cuenta.numero
        holder.lblSaldoCuentaItem.text = cuenta.saldo.toString() + " Bs"
        holder.itemView.isSelected = selectedPos == position

    }



    class SelectorCuentaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lblCuentaItem = itemView.findViewById<TextView>(R.id.lblCuentaItem)
        var lblSaldoCuentaItem = itemView.findViewById<TextView>(R.id.lblSaldoCuentaItem)
    }
}