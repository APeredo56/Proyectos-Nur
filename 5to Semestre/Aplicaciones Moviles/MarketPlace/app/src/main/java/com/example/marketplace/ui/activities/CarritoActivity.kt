package com.example.marketplace.ui.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.marketplace.dal.conn.AppDatabase
import com.example.marketplace.dal.dto.Producto
import com.example.marketplace.dal.dto.UnionVentaProducto
import com.example.marketplace.dal.dto.Venta
import com.example.marketplace.databinding.ActivityCarritoBinding
import com.example.marketplace.ui.adapters.CarritoAdapter
import java.time.Instant
import java.time.format.DateTimeFormatter

class CarritoActivity : AppCompatActivity(),
    CarritoAdapter.CarritoEventListener {

    lateinit var binding: ActivityCarritoBinding
     var productos: ArrayList<Producto> = arrayListOf()
    private lateinit var idProductos: ArrayList<Int>
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarritoBinding.inflate(layoutInflater)
        idProductos = intent.getIntegerArrayListExtra("carrito") as ArrayList<Int>
        db = AppDatabase.getInstance(this)
        for (id in idProductos) {
            val producto = db.productoDao().getById(id)
            if (producto != null) {
                producto.productoId = id
            }
            productos.add(producto!!)
        }
        setContentView(binding.root)
        setupRecyclerView()
        setupEventListeners()

    }

    override fun onBackPressed() {
        closeActivity()
        onBackPressedDispatcher.onBackPressed()
    }

    private fun setupRecyclerView() {
        val dividerItemDecoration =
            DividerItemDecoration(binding.rvCarrito.context, DividerItemDecoration.VERTICAL)

        binding.rvCarrito.apply {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this@CarritoActivity)
                    .apply {
                        orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                    }
            adapter = CarritoAdapter(productos, this@CarritoActivity)
            addItemDecoration(dividerItemDecoration)
        }
    }


    private fun setupEventListeners() {
        binding.btnCancelarCompra.setOnClickListener {
            closeActivity()
        }
        binding.btnComprar.setOnClickListener {
            val nombre = binding.txtName.editText?.text.toString()
            val nit = binding.txtNit.editText?.text.toString()
            val usuario = "aperedo"

            val createdAt = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
            if (nombre.isEmpty() || nit.isEmpty()) {
                Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val venta = Venta(nombre, nit.toInt(), usuario, createdAt, createdAt)
            val idVenta = db.VentaDao().insert(venta)
            productos.forEach {
                val registro = db.UnionVentaProductoDao().getById(idVenta.toInt(), it.productoId!!)
                if (registro != null) {
                    registro.cantidad += 1
                    db.UnionVentaProductoDao().update(registro)
                    return@forEach
                }
                db.UnionVentaProductoDao()
                    .insert(UnionVentaProducto(idVenta.toInt(), it.productoId!!, 1))
            }
            idProductos = arrayListOf()
            Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show()
            closeActivity()
        }
    }

    private fun closeActivity() {
        intent.putExtra("carrito", idProductos)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onProductoDeleteClick(producto: Producto) {
        val adapter = binding.rvCarrito.adapter as CarritoAdapter
        adapter.removeProducto(producto)
        productos.remove(producto)
    }

}