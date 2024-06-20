package com.example.capstone.data.api.services

import com.example.capstone.data.api.response.DashboardResponse
import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.api.response.UploadNewProductResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ProductApiService {
    @GET("products")
    suspend fun getAllProducts():List<GetAllProductResponseItem>

    @Multipart
    @POST("products")
    suspend fun uploadNewProduct(
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("price") price: RequestBody,
        @Part("category") category: RequestBody,
        @Part productImage: MultipartBody.Part
    ): UploadNewProductResponse

    @GET("orders/dashboard")
    suspend fun getDashboardData(): DashboardResponse

}