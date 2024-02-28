package com.example.banco.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.banco.R
import com.example.banco.databinding.FragmentSucursalesBinding
import com.example.banco.ui.viewModels.SucursalesViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SucursalesFragment : Fragment() {
    var map: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var viewModel: SucursalesViewModel

    private val callback = OnMapReadyCallback { googleMap ->
        this.map = googleMap
        googleMap.uiSettings.isMapToolbarEnabled = true
        enableCurrentLocationButton()
    }

    private lateinit var binding: FragmentSucursalesBinding

    @SuppressLint("MissingPermission")
    private fun enableCurrentLocationButton() {
        map?.isMyLocationEnabled = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSucursalesBinding.inflate(layoutInflater)
        viewModel = SucursalesViewModel()
        setupViewModelObservers()
        viewModel.verifyLocationEnabled(requireContext(), requireActivity())
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            enableCurrentLocationButton()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchListaSucursales(requireContext())

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun setupViewModelObservers() {
        viewModel.isMyLocationEnabled.observe(viewLifecycleOwner) {
            if (it) {
                enableCurrentLocationButton()
            }
        }

        viewModel.listaSucursales.observe(viewLifecycleOwner) {
            for (sucursal in it) {
                val mapLocation = LatLng(sucursal.latitud, sucursal.longitud)
                var nombreSucursal = ""
                when (sucursal.tipo) {
                    0 -> nombreSucursal = "Sucursal ${sucursal.direccion}"
                    1 -> nombreSucursal = "Cajero ${sucursal.direccion}"
                    2 -> nombreSucursal = "Sucursal con Cajero ${sucursal.direccion}"
                }
                var icon: Bitmap? = null
                when (sucursal.tipo) {
                    0 -> icon = BitmapFactory.decodeResource(resources, R.drawable.bank_icon)

                    1 -> icon = BitmapFactory.decodeResource(resources, R.drawable.cajero_icon)

                    2 -> icon = BitmapFactory.decodeResource(resources, R.drawable.bank_icon)
                }

                map?.addMarker(
                    MarkerOptions().position(mapLocation).title(nombreSucursal)
                        .icon(BitmapDescriptorFactory.fromBitmap(
                            Bitmap.createScaledBitmap(icon!!, 100, 100, false))))
            }
            try {
                val mapLocation = LatLng(it[0].latitud, it[0].longitud)
                map?.animateCamera(CameraUpdateFactory.newLatLngZoom(mapLocation, 15f))
            } catch (e: Exception) {
                Log.e("SUCURSAL", "Cargando Sucursales")
            }

        }
    }
}