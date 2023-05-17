package com.example.vinoteka.networking

import com.example.vinoteka.networking.model.WineResponse
import retrofit2.Response

class WineRepository {

    private val apiService: VinotekaApiService = RetrofitBuilder.apiService

    suspend fun getWines(): Response<WineResponse> {
        return apiService.getWines()
    }

    fun updateWine(wine: WineResponse) {
        apiService.updateWine(wine = wine)
    }
}