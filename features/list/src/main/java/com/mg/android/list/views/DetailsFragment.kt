package com.mg.android.list.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mg.android.common.base.BaseFragment
import com.mg.android.common.base.BaseViewModel
import com.mg.android.list.R
import com.mg.android.list.databinding.FragmentDetailsBinding
import com.mg.android.list.viewmodel.ListViewModel
import com.mg.android.model.Car
import com.mg.android.navigation.DetailFragmentArgs
import org.koin.android.viewmodel.ext.android.viewModel


class DetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val dviewModel: ListViewModel by viewModel()

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        (activity as AppCompatActivity?)?.supportActionBar?.show()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (args.car.id.isNotEmpty()) {
            showOnePinOnTheMap()
        } else {
            showAllPinsOnTheMap()
        }

    }

    override fun getViewModel(): BaseViewModel = dviewModel

    private fun showAllPinsOnTheMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.f_map) as SupportMapFragment?
        mapFragment!!.getMapAsync { map ->

            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            map.clear()

            dviewModel.cars.observe(this, Observer {
                it.data?.get(0)?.let {
                    zoomIn(map, it)
                }

                it.data?.let {
                    it.forEach {
                        addMarkersOnMap(map, it)
                    }
                }
            })
        }
    }

    private fun showOnePinOnTheMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.f_map) as SupportMapFragment?
        mapFragment!!.getMapAsync { map ->
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            map.clear()

            val car: Car = args.car
            zoomIn(map, car, true)
            addMarkersOnMap(map, car)
        }
    }

    private fun zoomIn(map: GoogleMap, car: Car, isOnePin : Boolean = false) {
        var zoom = 10f
        if (isOnePin) zoom = 18f

        val googlePlex = CameraPosition.builder()
            .zoom(zoom)
            .bearing(0f)
            .tilt(45f)
            .target(LatLng(car.latitude, car.longitude))
            .build()
        map.animateCamera(
            CameraUpdateFactory.newCameraPosition(googlePlex),
            5000,
            null
        )
    }


    private fun addMarkersOnMap(map: GoogleMap, car: Car) {
        map.addMarker(
            MarkerOptions()
                .position(LatLng(car.latitude, car.longitude))
                .title("${car.modelName} - ${car.name}")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)))

    }
}
