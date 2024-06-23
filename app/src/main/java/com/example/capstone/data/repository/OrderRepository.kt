package com.example.capstone.data.repository

import com.example.capstone.data.api.services.OrderRequest
import com.example.capstone.data.api.services.OrderResponse
import com.example.capstone.data.api.services.OrderService
import retrofit2.await

class OrderRepository private constructor(private val apiService: OrderService) {

    suspend fun createOrder(orderRequest: OrderRequest): OrderResponse {
        return apiService.createOrder(orderRequest).await()
    }
    suspend fun updateOrderStatus(orderId: String, status: Map<String, String>): OrderResponse {
        return apiService.updateOrderStatus(orderId, status)
    }
    companion object {
        @Volatile
        private var instance: OrderRepository? = null

        fun getInstance(apiService: OrderService): OrderRepository {
            return instance ?: synchronized(this) {
                instance ?: OrderRepository(apiService).also { instance = it }
            }
        }
    }
}