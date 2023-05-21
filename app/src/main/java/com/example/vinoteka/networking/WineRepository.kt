package com.example.vinoteka.networking

import com.example.vinoteka.model.OrderResponse
import com.example.vinoteka.model.Sort
import com.example.vinoteka.networking.model.WineRequest
import com.example.vinoteka.networking.model.WineResponse
import retrofit2.Response
import javax.inject.Inject

class WineRepository @Inject constructor(private val apiService: VinotekaApiService) {

    suspend fun getWines(): Response<List<WineResponse>> {
        return apiService.getWines()
    }

    suspend fun getWineById(id: Long): Response<WineResponse> {
        return apiService.getWineById(id = id)
    }

    suspend fun updateWineById(id: Long, wine: WineRequest): Response<Unit> {
        return apiService.updateWine(id = id, wine = wine)
    }

    suspend fun getOrders(id: Long): Response<List<OrderResponse>> {
        return apiService.getOrdersByWineId(id = id)
    }

    suspend fun getSorts(): Response<List<Sort>> {
        return apiService.getSorts()
    }

    suspend fun addWine(wine: WineRequest) {
        apiService.addWine(wine = wine)
    }

    suspend fun deleteWine(id: Long) {
        apiService.deleteWine(id = id)
    }
}