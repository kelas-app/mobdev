package com.example.capstone.data.api.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface OrderService {
    @POST("orders")
    fun createOrder(@Body request: OrderRequest): Call<OrderResponse>

    @PUT("orders/{orderId}")
    suspend fun updateOrderStatus(
        @Path("orderId") orderId: String,
        @Body status: Map<String, String>
    ): Call<OrderResponse>

    @DELETE("orders/{orderId}")
    suspend fun deleteOrder(
        @Path("orderId") orderId: String,
        @Body status: Map<String, String>
    ): Call<OrderResponse>
}