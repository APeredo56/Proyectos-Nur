package com.example.marketplace.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marketplace.databinding.ActivityProductoDetailBinding
import com.example.marketplace.dal.conn.AppDatabase
import com.example.marketplace.dal.dto.Producto
import java.time.Instant
import java.time.format.DateTimeFormatter

class ProductoDetailActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    lateinit var binding: ActivityProductoDetailBinding
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.getInstance(this)
        id = intent.getIntExtra("id", 0)
        if (id != 0) {
            loadProductoDetail()
        }
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnCancelProduct.setOnClickListener { finish() }
        binding.btnSaveProduct.setOnClickListener { saveProducto() }
    }

    private fun saveProducto() {
        val nombre = binding.txtProductName.editText?.text.toString()
        val descripcion = binding.txtProductDescription.editText?.text.toString()
        val precio = binding.txtPrice.editText?.text.toString().toDouble()
        val idCategoria = binding.txtCategoryId.editText?.text.toString().toInt()
        val createdAt = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        val producto = Producto(
            nombre,
            descripcion,
            precio,
            idCategoria,
            createdAt,
            createdAt
        )
        if (id == 0) {
            db.productoDao().insert(
                producto
            )
        } else {
            producto.productoId = id
            db.productoDao().update(
                producto
            )
        }
        finish()
    }

    private fun loadProductoDetail() {
        val producto = db.productoDao().getById(id)
        if (producto == null) {
            finish()
            return
        }
        loadProductoInForm(producto)
    }

    private fun loadProductoInForm(producto: Producto) {
        binding.txtProductName.editText?.setText(producto.nombre)
        binding.txtProductDescription.editText?.setText(producto.descripcion)
        binding.txtPrice.editText?.setText(producto.precio.toString())
        binding.txtCategoryId.editText?.setText(producto.idCategoria.toString())
    }


}