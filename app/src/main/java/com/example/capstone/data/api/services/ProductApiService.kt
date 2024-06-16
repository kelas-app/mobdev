package com.example.capstone.data.api.services

import com.example.capstone.data.api.response.GetAllProductResponse
import com.example.capstone.data.api.response.LoginResponse
import com.example.capstone.data.api.response.UploadNewProductResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApiService {
    @GET("products")
    suspend fun getAllProducts():GetAllProductResponse

//    @POST("products")
//    suspend fun uploadNewProduct(
//        @Body productRequest : ProductRequest
//    ): UploadNewProductResponse
}