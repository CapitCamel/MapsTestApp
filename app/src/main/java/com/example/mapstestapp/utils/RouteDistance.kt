package com.example.mapstestapp.utils

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil

class RouteDistance {
    //Расчет длины всего маршрута
    fun getDistance(list: List<LatLng>): Double {
        var distance: Double = 0.0
        for (i in 0..list.size - 2){
            distance += distanceBetweenTwoPoints(list[i], list[i + 1])
        }
        return distance
    }

    //Расчет длины между двумя точками
    private fun distanceBetweenTwoPoints(pointOne: LatLng, pointTwo: LatLng): Double {
        val distance =  SphericalUtil.computeDistanceBetween(pointOne , pointTwo)
        return distance/1000
    }
}