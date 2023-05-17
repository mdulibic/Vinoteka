package com.example.vinoteka.networking

import com.example.vinoteka.networking.model.WineResponse
import retrofit2.Response
import retrofit2.http.*

interface VinotekaApiService {
    @GET("wines")
    suspend fun getWines(): Response<WineResponse>

    @PUT("/updateWine")
    fun updateWine(wine: WineResponse): Response<Unit>
}
