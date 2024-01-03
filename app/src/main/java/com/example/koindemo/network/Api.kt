package com.example.koindemo.network

import com.example.koindemo.constants.Constants.BASE_URL
import com.example.koindemo.marvel.MarvelViewModel
import com.example.koindemo.repository.MarvelRepository
import com.example.koindemo.repository.MarvelRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { retrofitService() }
    single<MarvelRepository> { MarvelRepositoryImpl(get()) }
    viewModel { MarvelViewModel(get()) }
}

fun retrofitService(): ApiService {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}
