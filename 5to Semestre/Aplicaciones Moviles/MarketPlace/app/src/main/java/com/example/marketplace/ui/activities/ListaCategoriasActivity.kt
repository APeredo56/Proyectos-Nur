package com.example.marketplace.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.marketplace.databinding.ActivityListaCategoriasBinding
import com.example.marketplace.ui.adapters.CategoriaAdapter
import com.example.marketplace.dal.conn.AppDatabase
import com.example.marketplace.dal.dto.Categoria
import com.example.marketplace.models.CategoriaDeleteResponse
import com.example.marketplace.models.Venta
import com.example.marketplace.network.NetworkVerifier
import com.example.marketplace.repositories.CategoriaRepository
import com.example.marketplace.repositories.ProductoRepository
import com.example.marketplace.repositories.VentaRepository
import com.example.marketplace.synchronizers.CategoriaSynchronizer
import com.example.marketplace.synchronizers.ProductoSynchronizer
import com.example.marketplace.synchronizers.VentaSynchronizer

class ListaCategoriasActivity : AppCompatActivity(), CategoriaAdapter.CategoriaEventListener,
    CategoriaRepository.CategoriaDeleteListener, CategoriaRepository.CategoriaListListener,
    ProductoRepository.ProductoListListener, VentaRepository.VentaListListener {
    private lateinit var db: AppDatabase
    lateinit var binding: ActivityListaCategoriasBinding
    private var networkVerifier = NetworkVerifier(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaCategoriasBinding.inflate(layoutInflater)
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
    }

    private fun sincronizarApi() {
        CategoriaRepository.fetchListaCategorias(this)
        ProductoRepository.fetchListaProductos(this)
        VentaRepository.fetchListaVentas(this)

    }

    private fun reloadList() {
        binding.rvCategorias.adapter.let { adapter ->
            if (adapter is CategoriaAdapter) {
                adapter.updateList(db.categoriaDao().getAll())
            }
        }
    }

    private fun setupEventListeners() {
        binding.fabAdd.setOnClickListener {
            openDetailPage()
        }
    }

    private fun openDetailPage() {
        val intent = Intent(this, CategoriaDetailActivity::class.java)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        val dividerItemDecoration =
            DividerItemDecoration(binding.rvCategorias.context, DividerItemDecoration.VERTICAL)

        binding.rvCategorias.apply {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this@ListaCategoriasActivity)
                    .apply {
                        orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                    }
            adapter = CategoriaAdapter(arrayListOf(), this@ListaCategoriasActivity)
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onCategoriaClick(categoria: Categoria) {
        val intent = Intent(this, CategoriaDetailActivity::class.java)
        intent.putExtra("id", categoria.id)
        startActivity(intent)
    }

    override fun onCategoriaDeleteClick(categoria: Categoria) {
        CategoriaRepository.deleteCategoria(categoria.id!!, this)
        db.categoriaDao().delete(categoria)
        reloadList()
    }

    override fun onCategoriaDeleteSuccess(categoriaDeleteResponse: CategoriaDeleteResponse) {
        Toast.makeText(this, "Categoria eliminada", Toast.LENGTH_SHORT).show()
    }

    override fun onCategoriaDeleteError(t: Throwable) {
        Toast.makeText(this, "Error al eliminar categoria", Toast.LENGTH_SHORT).show()
    }

    override fun onCategoriaListFetched(categorias: List<com.example.marketplace.models.Categoria>) {
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