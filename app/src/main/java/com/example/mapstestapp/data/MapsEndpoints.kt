package com.example.mapstestapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MapsEndpoints {
    @GET("/api/russia.geo.json")
    suspend fun getCoordinates(): MapsData
}