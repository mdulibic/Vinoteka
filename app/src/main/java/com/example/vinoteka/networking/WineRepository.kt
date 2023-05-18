package com.example.vinoteka.networking

import com.example.vinoteka.model.OrderResponse
import com.example.vinoteka.model.Sort
import com.example.vinoteka.networking.model.WineResponse
import retrofit2.Response
import javax.inject.Inject

class WineRepository @Inject constructor(private val apiService: VinotekaApiService) {

    suspend fun getWines(): Response<List<WineResponse>> {
        return apiService.getWines()
    }

    suspend fun getOrders(id: String): Response<List<OrderResponse>> {
        return apiService.getOrdersByWineId(id = id)
    }

    suspend fun getSorts(): Response<List<Sort>> {
        return apiService.getSorts()
    }

    suspend fun addWine(wine: WineResponse) {
        apiService.addWine(wine = wine)
    }

    suspend fun deleteWine(id: String) {
        apiService.deleteWine(id = id)
    }
}