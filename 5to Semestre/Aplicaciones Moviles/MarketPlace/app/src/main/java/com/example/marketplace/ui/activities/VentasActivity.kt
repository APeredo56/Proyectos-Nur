package com.example.marketplace.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.marketplace.dal.conn.AppDatabase
import com.example.marketplace.dal.dto.Venta
import com.example.marketplace.databinding.ActivityListaVentasBinding
import com.example.marketplace.models.Categoria
import com.example.marketplace.models.VentaDeleteResponse
import com.example.marketplace.network.NetworkVerifier
import com.example.marketplace.repositories.CategoriaRepository
import com.example.marketplace.repositories.ProductoRepository
import com.example.marketplace.repositories.VentaRepository
import com.example.marketplace.synchronizers.CategoriaSynchronizer
import com.example.marketplace.synchronizers.ProductoSynchronizer
import com.example.marketplace.synchronizers.VentaSynchronizer
import com.example.marketplace.ui.adapters.ProductoAdapter
import com.example.marketplace.ui.adapters.VentaAdapter

class VentasActivity : AppCompatActivity(), CategoriaRepository.CategoriaListListener,
    ProductoRepository.ProductoListListener, VentaAdapter.OnVentaClickListener,
    VentaRepository.VentaDeleteListener, VentaRepository.VentaListListener {
    private lateinit var db: AppDatabase
    lateinit var binding: ActivityListaVentasBinding
    private var networkVerifier = NetworkVerifier(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaVentasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.getInstance(this)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        if (networkVerifier.isConnected()){
            sincronizarApi()
        }
    }

    private fun sincronizarApi() {
        CategoriaRepository.fetchListaCategorias(this)
        ProductoRepository.fetchListaProductos(this)
        VentaRepository.fetchListaVentas(this)

    }

    private fun setupRecyclerView() {
        val ventas = db.VentaDao().getAll() as ArrayList
        val dividerItemDecoration =
            DividerItemDecoration(binding.rvVentas.context, DividerItemDecoration.VERTICAL)

        binding.rvVentas.apply {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this@VentasActivity)
                    .apply {
                        orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                    }
            adapter = VentaAdapter(ventas, db, this@VentasActivity)
            addItemDecoration(dividerItemDecoration)
        }
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

    override fun onVentaDeleteClick(venta: Venta) {
        VentaRepository.deleteVenta(venta.ventaId!!, this)
        db.VentaDao().delete(venta)
        db.UnionVentaProductoDao().deleteByVentaId(venta.ventaId!!)
        binding.rvVentas.adapter.let { adapter ->
            if (adapter is VentaAdapter) {
                adapter.updateList(db.VentaDao().getAll() as ArrayList<Venta>)
            }
        }

    }

    override fun onVentaDeleteSuccess(ventaDeleteResponse: VentaDeleteResponse) {
        Toast.makeText(this, "Venta eliminada", Toast.LENGTH_SHORT).show()
    }

    override fun onVentaDeleteError(t: Throwable) {
        Toast.makeText(this, "Error al eliminar venta", Toast.LENGTH_SHORT).show()
    }

    override fun onFetchListaVentasSuccess(ventas: List<com.example.marketplace.models.Venta>) {
        VentaSynchronizer(this, ventas).sync()
    }

    override fun onFetchListaVentasError(t: Throwable) {

    }
}