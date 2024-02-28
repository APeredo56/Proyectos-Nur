package com.example.marketplace.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.marketplace.databinding.ActivityListaProductosBinding
import com.example.marketplace.ui.adapters.ProductoAdapter
import com.example.marketplace.dal.conn.AppDatabase
import com.example.marketplace.dal.dto.Producto
import com.example.marketplace.models.Categoria
import com.example.marketplace.models.ProductoDeleteResponse
import com.example.marketplace.models.Venta
import com.example.marketplace.network.NetworkVerifier
import com.example.marketplace.repositories.CategoriaRepository
import com.example.marketplace.repositories.ProductoRepository
import com.example.marketplace.repositories.VentaRepository
import com.example.marketplace.synchronizers.CategoriaSynchronizer
import com.example.marketplace.synchronizers.ProductoSynchronizer
import com.example.marketplace.synchronizers.VentaSynchronizer


class ListaProductosActivity: AppCompatActivity(), ProductoAdapter.ProductoEventListener,
    CategoriaRepository.CategoriaListListener, ProductoRepository.ProductoListListener,
    ProductoRepository.ProductoDeleteListener, VentaRepository.VentaListListener {
    private lateinit var db: AppDatabase
    lateinit var binding: ActivityListaProductosBinding
    private var networkVerifier = NetworkVerifier(this)


    var shoppingCart = arrayListOf<Int>()
    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent = result.data!!
                shoppingCart = data.getIntegerArrayListExtra("carrito") as ArrayList<Int>
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.getInstance(this)
        setupRecyclerView()
        setupEventListeners()
    }

    override fun onResume() {
        super.onResume()
        if (networkVerifier.isConnected()){
            sincronizarApi()
        }
        reloadList()
        binding.txtItemsInCart.text = shoppingCart.size.toString()
    }

    private fun sincronizarApi() {
        CategoriaRepository.fetchListaCategorias(this)
        ProductoRepository.fetchListaProductos(this)
        VentaRepository.fetchListaVentas(this)

    }

    override fun onBackPressed() {
        intent.putExtra("carrito", shoppingCart)
        setResult(Activity.RESULT_OK, intent)
        finish()
        onBackPressedDispatcher.onBackPressed()
    }

    private fun reloadList() {
        binding.rvProductos.adapter.let { adapter ->
            if (adapter is ProductoAdapter) {
                adapter.updateList(db.productoDao().getAll())
            }
        }
    }

    private fun setupEventListeners() {
        binding.fabAdd.setOnClickListener {
            openDetailPage()
        }
        binding.btnShoppingCart.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            intent.putExtra("carrito", shoppingCart)
            resultLauncher.launch(intent)
        }
    }

    private fun openDetailPage() {
        val intent = Intent(this, ProductoDetailActivity::class.java)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        val dividerItemDecoration =
            DividerItemDecoration(binding.rvProductos.context, DividerItemDecoration.VERTICAL)

        binding.rvProductos.apply {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this@ListaProductosActivity)
                    .apply {
                        orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                    }
            adapter = ProductoAdapter(arrayListOf(), this@ListaProductosActivity)
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onProductoClick(producto: Producto) {
        val intent = Intent(this, ProductoDetailActivity::class.java)
        intent.putExtra("id", producto.productoId)
        startActivity(intent)
    }

    override fun onProductoDeleteClick(producto: Producto) {
        ProductoRepository.deleteProducto(producto.productoId!!, this)
        db.productoDao().delete(producto)
        reloadList()
    }

    override fun onAddProductoToCartClick(producto: Producto) {
        shoppingCart.add(producto.productoId!!)
        binding.txtItemsInCart.text = shoppingCart.size.toString()
        Toast.makeText(this, "Producto agregado al carrito", Toast.LENGTH_SHORT).show()
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

    override fun onProductoDeleteSuccess(response: ProductoDeleteResponse) {

    }

    override fun onProductoDeleteError(t: Throwable) {
        Toast.makeText(this, "Error al eliminar producto", Toast.LENGTH_SHORT).show()
    }

    override fun onFetchListaVentasSuccess(ventas: List<Venta>) {
        VentaSynchronizer(this, ventas).sync()
    }

    override fun onFetchListaVentasError(t: Throwable) {

    }

}