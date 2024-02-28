package com.example.banco.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banco.R
import com.example.banco.api.model.Extracto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter




class ExtractosAdapter(var extractos: ArrayList<Extracto>) :
    RecyclerView.Adapter<ExtractosAdapter.CuentaDetailViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuentaDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_extracto, parent, false)
        return CuentaDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return extractos.size
    }

    override fun onBindViewHolder(holder: CuentaDetailViewHolder, position: Int) {
        val extracto = extractos[position]
        val localDateTime = LocalDateTime.parse(extracto.created_at.substring(0, 19))
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        val fecha = formatter.format(localDateTime)
        holder.lblFecha.text = fecha
        holder.lblDescripcion.text = extracto.descripcion
        holder.lblMonto.text = extracto.monto.toString() + " Bs"
        holder.lblDestino.text = extracto.cuentaDestino
        if (extracto.tipo == -1) {
            holder.lblTipo.setImageResource(R.drawable.egreso_icon)
            holder.lblMonto.text = extracto.monto.toString()
        } else {
            holder.lblTipo.setImageResource(R.drawable.ingreso_icon)
            holder.lblMonto.text = "+" + extracto.monto.toString()
        }
    }

    class CuentaDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lblFecha = itemView.findViewById<TextView>(R.id.lblFecha)
        var lblDescripcion = itemView.findViewById<TextView>(R.id.lblDescripcion)
        var lblMonto = itemView.findViewById<TextView>(R.id.lblMonto)
        var lblTipo = itemView.findViewById<ImageView>(R.id.lblTipo)
        var lblDestino = itemView.findViewById<TextView>(R.id.lblDestino)
    }
}