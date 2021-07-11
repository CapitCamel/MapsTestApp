package com.example.mapstestapp.di

import com.example.mapstestapp.data.MapsEndpoints
import com.example.mapstestapp.ui.MainViewModel
import com.example.mapstestapp.utils.Converter
import com.example.mapstestapp.utils.RouteDistance
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val apiModule = module {
    fun provideEndpoints(retrofit: Retrofit): MapsEndpoints {
        return retrofit.create(MapsEndpoints::class.java)
    }

    single { provideEndpoints(get()) }
}

val networkModule = module {
    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://waadsu.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single {
        provideRetrofit(get())
    }
    single { provideHttpClient() }
}


val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}

val utilModule = module {
    single { Converter() }
    single { RouteDistance() }
}
