package com.example.marketplace.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.R
import com.example.marketplace.dal.dto.Producto

class CarritoAdapter (
    var data: ArrayList<Producto>,
    val listener: CarritoEventListener
) : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.carrito_item, parent, false)
        return CarritoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val producto = data[position]
        holder.lblNombreProducto.text = producto.nombre
        holder.lblPrecioProducto.text = producto.precio.toString()

        holder.btnDelProductoCarrito.setOnClickListener {
            listener.onProductoDeleteClick(producto)
        }
    }

    fun removeProducto(producto: Producto) {
        val posicion = data.indexOf(producto)
        data.remove(producto)
        notifyItemRemoved(posicion)
    }

    class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lblNombreProducto = itemView.findViewById<TextView>(R.id.lblNombreProducto)
        var lblPrecioProducto = itemView.findViewById<TextView>(R.id.lblPrecioProducto)
        val btnDelProductoCarrito = itemView.findViewById<ImageButton>(R.id.btnDelProductoCarrito)
    }

    interface CarritoEventListener {
        fun onProductoDeleteClick(producto: Producto)
    }
}