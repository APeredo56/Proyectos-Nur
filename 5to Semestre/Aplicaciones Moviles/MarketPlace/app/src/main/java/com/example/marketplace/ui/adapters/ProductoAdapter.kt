package com.example.marketplace.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.R
import com.example.marketplace.dal.dto.Producto

class ProductoAdapter(
    var data: MutableList<Producto>,
    val listener: ProductoEventListener
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.producto_list_item, parent, false)
        return ProductoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = data[position]
        holder.lblProductName.text = producto.nombre
        holder.lblProductPrice.text = "$${producto.precio}"

        holder.itemView.setOnClickListener {
            listener.onProductoClick(producto)
        }
        holder.lblProductName.setOnClickListener {
            listener.onProductoClick(producto)
        }
        holder.lblProductPrice.setOnClickListener {
            listener.onProductoClick(producto)
        }
        holder.btnAddToCart.setOnClickListener {
            listener.onAddProductoToCartClick(producto)
        }
        holder.btnDeleteProductItem.setOnClickListener {
            listener.onProductoDeleteClick(producto)
        }
    }

    fun updateList(productos: List<Producto>?) {
        if (productos == null) return
        this.data = productos as MutableList<Producto>
        notifyDataSetChanged()
    }


    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lblProductName = itemView.findViewById<TextView>(R.id.lblProductName)
        var lblProductPrice = itemView.findViewById<TextView>(R.id.lblProductPrice)
        val btnAddToCart = itemView.findViewById<ImageButton>(R.id.btnAddToCart)
        val btnDeleteProductItem = itemView.findViewById<ImageButton>(R.id.btnDeleteProductItem)
    }

    interface ProductoEventListener {
        fun onProductoClick(producto: Producto)
        fun onProductoDeleteClick(producto: Producto)
        fun onAddProductoToCartClick(producto: Producto)
    }
}