package com.example.mapstestapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.example.mapstestapp.R
import com.example.mapstestapp.databinding.ActivityMainBinding
import com.example.mapstestapp.utils.Converter
import com.example.mapstestapp.utils.RouteDistance
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    private val viewModel by viewModel<MainViewModel>()
    private val routeDistance: RouteDistance by inject()
    private val converter: Converter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bottomSheetBehavior = BottomSheetBehavior.from(binding.btmsh.root)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.maps) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onMapReady(p0: GoogleMap) {
        //Беру данные из лайвдаты во вьюмодели
        viewModel.coordinates.observe(this){
            //Линия по координатам

            val polyline = p0.addPolyline(
                PolylineOptions()
                    .clickable(true)
                    .addAll(converter.getListLatLng(it[0].geometry.coordinates)))

            //вешаю на линию стиль и ClickListener
            stylePolyline(polyline)
            p0.setOnPolylineClickListener(this)


            binding.btmsh.distance.text =
                routeDistance
                    .getDistance(converter.getListLatLng(it[0].geometry.coordinates))
                    .toString() + " km"
        }
    }

    //Появление BottomSheet с длиной маршрута при клике на линию если BottomSheet скрыт
    override fun onPolylineClick(p0: Polyline) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    //Стиль для линии
    private fun stylePolyline(polyline: Polyline) {
        polyline.startCap = RoundCap()
        polyline.endCap = RoundCap()
        polyline.color = R.color.arrow_color
        polyline.jointType = JointType.ROUND
    }
}