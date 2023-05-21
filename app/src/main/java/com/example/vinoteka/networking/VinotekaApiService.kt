package com.example.vinoteka.networking

import com.example.vinoteka.model.OrderResponse
import com.example.vinoteka.model.Sort
import com.example.vinoteka.networking.model.WineRequest
import com.example.vinoteka.networking.model.WineResponse
import retrofit2.Response
import retrofit2.http.*

interface VinotekaApiService {
    @GET("/wine")
    suspend fun getWines(): Response<List<WineResponse>>

    @GET("/wine/{id}")
    suspend fun getWineById(@Path("id") id: Long): Response<WineResponse>

    @GET("/wine/{id}/orders")
    suspend fun getOrdersByWineId(@Path("id") id: Long): Response<List<OrderResponse>>

    @POST("/wine")
    suspend fun addWine(@Body wine: WineRequest): Response<Unit>

    @DELETE("/wine/{id}")
    suspend fun deleteWine(@Path("id") id: Long): Response<Unit>

    @PUT("/{id}")
    suspend fun updateWine(@Path("id") id: Long, @Body wine: WineRequest): Response<Unit>

    @GET("/sort")
    suspend fun getSorts(): Response<List<Sort>>
}
