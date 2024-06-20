package com.example.capstone.data.api.services

import com.example.capstone.data.api.response.ConversationsResponseItem
import com.example.capstone.data.api.response.GetAllProductNewResponseItem
import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.api.response.GetCategoryProductResponse
import com.example.capstone.data.api.response.GetCategoryProductResponseItem
import com.example.capstone.data.api.response.GetDetailProductResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Path
import com.example.capstone.data.api.response.DashboardResponse
import com.example.capstone.data.api.response.UploadNewProductResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ProductApiService {
    @POST("products/recommend")
    suspend fun getAllProducts(
        @Body request: ProductRequestRecommend
    ): List<GetAllProductResponseItem>

    @GET("products/{id}")
    suspend fun detailProduct(
        @Path("id") id: String
    ): GetDetailProductResponse

    @GET("products/category/{category}")
    suspend fun categoryProduct(
        @Path("category") category: String
    ): List<GetCategoryProductResponseItem>

    @GET("products/")
    suspend fun getAllNewProducts(): List<GetAllProductNewResponseItem>

    @GET("conversations/{userId}")
    suspend fun getAllChat(
        @Path("userId") userId: String
    ): List<ConversationsResponseItem>

    @POST("conversations")
    suspend fun createConversations(
        @Body request: ConversationsRequest
    ): List<ConversationsResponseItem>
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

