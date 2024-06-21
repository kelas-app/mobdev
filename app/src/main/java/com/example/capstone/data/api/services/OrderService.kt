package com.example.capstone.data.api.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderService {
    @POST("orders")
    fun createOrder(@Body request: OrderRequest): Call<OrderResponse>
}