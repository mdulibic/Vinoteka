package com.example.vinoteka.networking

import com.example.vinoteka.model.OrderResponse
import com.example.vinoteka.model.Sort
import com.example.vinoteka.networking.model.AddWineRequest
import com.example.vinoteka.networking.model.WineResponse
import retrofit2.Response
import retrofit2.http.*

interface VinotekaApiService {
    @GET("/wines")
    suspend fun getWines(): Response<List<WineResponse>>

    @GET("/sorts")
    suspend fun getSorts(): Response<List<Sort>>

    @GET("/orders")
    suspend fun getOrdersByWineId(id: String): Response<List<OrderResponse>>

    @DELETE("/wines")
    suspend fun deleteWine(id: String): Response<Unit>

    @POST("/addWine")
    suspend fun addWine(wine: AddWineRequest): Response<Unit>

    @PUT("/updateWine")
    fun updateWine(wine: WineResponse): Response<Unit>
}
