package com.example.marketplace.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.R
import com.example.marketplace.dal.dto.Categoria

class CategoriaAdapter(
    var data: MutableList<Categoria>,
    val listener: CategoriaEventListener
) : RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.categoria_list_item, parent, false)
        return CategoriaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = data[position]
        holder.lblCategoryName.text = categoria.nombre
        holder.itemView.setOnClickListener {
            listener.onCategoriaClick(categoria)
        }
        holder.lblCategoryName.setOnClickListener {
            listener.onCategoriaClick(categoria)
        }
        holder.btnDeleteCategoryItem.setOnClickListener {
            listener.onCategoriaDeleteClick(categoria)
        }
    }

    fun updateList(categorias: List<Categoria>?) {
        if (categorias == null) return
        this.data = categorias as MutableList<Categoria>
        notifyDataSetChanged()
    }


    class CategoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lblCategoryName = itemView.findViewById<TextView>(R.id.lblCategoryName)
        val btnDeleteCategoryItem = itemView.findViewById<ImageButton>(R.id.btnDeleteCategoryItem)
    }

    interface CategoriaEventListener {
        fun onCategoriaClick(categoria: Categoria)
        fun onCategoriaDeleteClick(categoria: Categoria)
    }
}