package com.example.vinoteka.networking

import com.example.vinoteka.model.OrderResponse
import com.example.vinoteka.model.Sort
import com.example.vinoteka.networking.model.AddWineRequest
import com.example.vinoteka.networking.model.WineResponse
import retrofit2.Response
import retrofit2.http.*

interface VinotekaApiService {
    @GET("/wine")
    suspend fun getWines(): Response<List<WineResponse>>

    @GET("/wine/{id}")
    suspend fun getWineById(@Path("id") id: String): Response<WineResponse>

    @GET("/wine/{id}/orders")
    suspend fun getOrdersByWineId(@Path("id") id: String): Response<List<OrderResponse>>

    @POST("/wine")
    suspend fun addWine(wine: AddWineRequest): Response<Unit>

    @DELETE("/wine/{id}")
    suspend fun deleteWine(@Path("id") id: String): Response<Unit>

    @PUT("/{id}")
    fun updateWine(@Path("id") id: String, wine: WineResponse): Response<Unit>

    @GET("/sort")
    suspend fun getSorts(): Response<List<Sort>>
}
