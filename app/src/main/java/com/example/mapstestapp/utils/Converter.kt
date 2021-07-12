package com.example.mapstestapp.utils

import com.google.android.gms.maps.model.LatLng

class Converter {
    //Конвертация результатов запроса в LatLng
    fun getListLatLng(listDouble: List<List<List<List<Double>>>>): List<LatLng> {
        val listLatLng = arrayListOf<LatLng>()
        for (points in listDouble) {
            for (point in points[0]) {
                var lat = point[1]
                var lng = point[0]

                if (lat < 90 && lat > -90) {
                    if (lng < 180 && lng > -180) {
                        //дальше этих значений они не могут быть
                        listLatLng.add(LatLng(lat, lng))
                    }
                }
            }
        }
        return listLatLng
    }
}