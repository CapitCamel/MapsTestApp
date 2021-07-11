package com.example.mapstestapp.data

data class MapsData(
    val type : String,
    val features : List<Features>
)

data class Features (
    val geometry : Geometry
)
data class Geometry (
    val type : String,
    val coordinates : List<List<List<List<Double>>>>
)