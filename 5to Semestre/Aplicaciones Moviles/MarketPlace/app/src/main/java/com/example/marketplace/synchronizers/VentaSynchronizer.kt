package com.example.marketplace.synchronizers

import android.content.Context
import android.util.Log
import com.example.marketplace.dal.conn.AppDatabase
import com.example.marketplace.dal.dto.UnionVentaProducto
import com.example.marketplace.dal.dto.Venta
import com.example.marketplace.models.Producto
import com.example.marketplace.repositories.VentaRepository
import java.time.LocalDateTime

class VentaSynchronizer(
    val context: Context,
    val ventasApi: List<com.example.marketplace.models.Venta>
) : VentaRepository.VentaInsertListener {
    private var db: AppDatabase = AppDatabase.getInstance(context)
    private var idConflictoDB = 0
    fun sync() {
        val ventasDB = db.VentaDao().getAll() as MutableList
        for (ventaApi in ventasApi) {
            val ventaDB = ventasDB.find { it.ventaId == ventaApi.id }
            if (ventaDB == null) {
                //Estan en la api pero no en la db

                val newVenta = Venta(
                    ventaApi.nombre,
                    ventaApi.nit,
                    ventaApi.usuario,
                    ventaApi.created_at,
                    ventaApi.updated_at
                )
                newVenta.ventaId = ventaApi.id
                db.VentaDao().insert(newVenta)
                for (detalle in ventaApi.detalle) {
                    db.UnionVentaProductoDao().insert(
                        UnionVentaProducto(
                            ventaApi.id,
                            detalle.producto.id,
                            detalle.cantidad
                        )
                    )
                }
                //Importado el detalle de la api
            } else if (ventaDB.createdAt == ventaApi.created_at){
                ventasDB.remove(ventaDB)
            } else {
                ventasDB.remove(ventaDB)
            }
            val lastDateApi = LocalDateTime.parse(ventasApi.last().created_at.substring(0,19))
            /*for (ventaDB in ventasDB){
                val dateVentaDb = LocalDateTime.parse(ventaDB.createdAt.substring(0,19))
                if (lastDateApi.isBefore(dateVentaDb)){
                    var productos = ArrayList<Producto>()
                    productos = db.VentaDao().getVentaConProductos(ventaDB.ventaId!!)[0].productos
                            as ArrayList<Producto>
                    db.VentaDao().delete(ventaDB)
                    idConflictoDB = ventaDB.ventaId!!
                    VentaRepository.saveInsertVenta(
                        ventaDB.cliente,
                        ventaDB.nit,
                        ventaDB.usuario,
                        productos,
                        this
                    )

                } else{
                    db.VentaDao().delete(ventaDB)
                    db.UnionVentaProductoDao().deleteByVentaId(ventaDB.ventaId!!)
                }
            }*/
        }
    }

    override fun onVentaInsertSuccess(venta: com.example.marketplace.models.Venta) {
        if (idConflictoDB != 0){
            val newVenta = Venta(
                venta.nombre,
                venta.nit,
                venta.usuario,
                venta.created_at,
                venta.updated_at
            )
            newVenta.ventaId = venta.id
            db.VentaDao().insert(newVenta)
            idConflictoDB = 0
        }
    }

    override fun onVentaInsertError(t: Throwable) {

    }
}