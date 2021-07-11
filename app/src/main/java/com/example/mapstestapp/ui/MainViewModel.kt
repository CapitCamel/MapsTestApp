package com.example.mapstestapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapstestapp.data.*
import kotlinx.coroutines.launch

class MainViewModel(private val api: MapsEndpoints): ViewModel() {

    private val _coordinates = MutableLiveData<List<Features>>()
    val coordinates: LiveData<List<Features>>
        get()=_coordinates

    init {
        getPopularMovies()
    }

    private fun getPopularMovies(){
        viewModelScope.launch {
            try {
                _coordinates.value = api.getCoordinates().features
            } catch (e: Exception) {
                Log.d("MAP_ER", e.message.toString())
            }
        }
    }
}