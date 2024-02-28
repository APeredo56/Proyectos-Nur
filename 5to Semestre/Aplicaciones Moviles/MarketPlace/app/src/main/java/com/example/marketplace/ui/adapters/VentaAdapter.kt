package com.example.marketplace.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.R
import com.example.marketplace.dal.conn.AppDatabase
import com.example.marketplace.dal.dto.Venta

class VentaAdapter(var data: ArrayList<Venta>, var db: AppDatabase, var listener: OnVentaClickListener) :
    RecyclerView.Adapter<VentaAdapter.VentaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VentaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.venta_list_item, parent, false)
        return VentaAdapter.VentaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: VentaViewHolder, position: Int) {
        val venta = data[position]
        holder.lblNombreVenta.text = venta.cliente
        holder.lblNit.text = venta.nit.toString()
        val productos = db.VentaDao().getVentaConProductos(venta.ventaId!!)
        var detalleProductos = ""
        productos.forEach {
            it.productos.forEach {
                val cantidad =
                    db.UnionVentaProductoDao().getById(venta.ventaId!!, it.productoId!!)?.cantidad
                detalleProductos += "${it.nombre} - ${cantidad} unidades - $${it.precio*cantidad!!} \n"
            }
        }
        holder.txtDetalleProductos.text = detalleProductos
        holder.btnDeleteVentaItem.setOnClickListener {
            listener.onVentaDeleteClick(venta)
        }
    }

    fun updateList(newData: ArrayList<Venta>){
        data = newData
        notifyDataSetChanged()
    }

    class VentaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblNombreVenta = itemView.findViewById<TextView>(R.id.lblNombreVenta)
        val lblNit = itemView.findViewById<TextView>(R.id.lblNit)
        val txtDetalleProductos = itemView.findViewById<TextView>(R.id.txtDetalleProductos)
        val btnDeleteVentaItem = itemView.findViewById<ImageButton>(R.id.btnDeleteVentaItem)
    }

    interface OnVentaClickListener {
        fun onVentaDeleteClick(venta: Venta)
    }
}