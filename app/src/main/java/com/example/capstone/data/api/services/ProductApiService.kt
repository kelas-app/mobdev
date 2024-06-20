package com.example.capstone.data.api.services

import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.api.response.UploadNewProductResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApiService {
    @GET("products")
    suspend fun getAllProducts():List<GetAllProductResponseItem>


    @POST("products")
    suspend fun uploadNewProduct(
        @Body addProduct: AddProduct
    ): UploadNewProductResponse

}