package com.example.marketplace.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.marketplace.dal.conn.AppDatabase
import com.example.marketplace.databinding.ActivityMenuBinding
import com.example.marketplace.models.Categoria
import com.example.marketplace.models.Venta
import com.example.marketplace.network.NetworkVerifier
import com.example.marketplace.repositories.CategoriaRepository
import com.example.marketplace.repositories.ProductoRepository
import com.example.marketplace.repositories.VentaRepository
import com.example.marketplace.synchronizers.CategoriaSynchronizer
import com.example.marketplace.synchronizers.ProductoSynchronizer
import com.example.marketplace.synchronizers.VentaSynchronizer

class MenuActivity : AppCompatActivity(), CategoriaRepository.CategoriaListListener,
    ProductoRepository.ProductoListListener, VentaRepository.VentaListListener {
    var shoppingCart = arrayListOf<Int>()
    lateinit var binding: ActivityMenuBinding
    lateinit var db: AppDatabase
    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent = result.data!!
                shoppingCart =
                    data.getIntegerArrayListExtra("carrito") as ArrayList<Int>
            }
        }
    private var networkVerifier = NetworkVerifier(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        db = AppDatabase.getInstance(this)
        setContentView(binding.root)
        setupEventListeners()
    }

    override fun onResume() {
        super.onResume()
        if (networkVerifier.isConnected()){
            sincronizarApi()
        }
        binding.txtItemsInCart2.text = shoppingCart.size.toString()
    }

    private fun setupEventListeners() {
        binding.btnGoToCategoryList.setOnClickListener {
            startActivity(Intent(this, ListaCategoriasActivity::class.java))
        }
        binding.btnGoToProductoList.setOnClickListener {
            val intent = Intent(this, ListaProductosActivity::class.java)
            intent.putIntegerArrayListExtra("carrito", shoppingCart)
            resultLauncher.launch(intent)
        }
        binding.btnShoppingCart.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            intent.putIntegerArrayListExtra("carrito", shoppingCart)
            resultLauncher.launch(intent)
        }
        binding.btnVentas.setOnClickListener {
            val intent = Intent(this, VentasActivity::class.java)
            intent.putIntegerArrayListExtra("carrito", shoppingCart)
            resultLauncher.launch(intent)
        }
    }

    private fun sincronizarApi() {
        CategoriaRepository.fetchListaCategorias(this)
        ProductoRepository.fetchListaProductos(this)
        VentaRepository.fetchListaVentas(this)
    }

    override fun onCategoriaListFetched(categorias: List<Categoria>) {
        CategoriaSynchronizer(this, categorias).sync()

    }

    override fun onCategoriaListFetchError(t: Throwable) {
        Toast.makeText(this, "Error al sincronizar categorias", Toast.LENGTH_SHORT).show()
    }

    override fun onFetchListaProductosSuccess(productos: List<com.example.marketplace.models.Producto>) {
        ProductoSynchronizer(this, productos).sync()
    }

    override fun onFetchListaProductosError(t: Throwable) {
        Toast.makeText(this, "Error al sincronizar productos", Toast.LENGTH_SHORT).show()
    }

    override fun onFetchListaVentasSuccess(ventas: List<Venta>) {
        VentaSynchronizer(this, ventas).sync()
    }

    override fun onFetchListaVentasError(t: Throwable) {

    }
}