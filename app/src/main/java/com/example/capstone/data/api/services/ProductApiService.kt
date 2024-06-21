package com.example.capstone.data.api.services

import com.example.capstone.data.api.response.CartItem
import com.example.capstone.data.api.response.ConversationsResponseItem
import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.api.response.GetCategoryProductResponseItem
import com.example.capstone.data.api.response.GetDetailProductResponse
import com.example.capstone.data.api.response.SearchProductResponseItem
import com.example.capstone.data.api.response.UploadNewProductResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.Call
import com.example.capstone.data.api.response.Order
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.PUT
import com.example.capstone.data.api.response.DashboardResponse
import com.example.capstone.data.api.response.Data
import com.example.capstone.data.api.response.LoginResponse
import retrofit2.http.Query

interface ProductApiService {
    @POST("products/recommend")
    suspend fun getAllProducts(
        @Body request: ProductRequestRecommend
    ): List<GetAllProductResponseItem>

    @GET("cart/view")
    suspend fun getCartItems(): List<CartItem>

    @GET("products/{id}")
    suspend fun detailProduct(
        @Path("id") id: String
    ): GetDetailProductResponse

    @GET("products/category/{category}")
    suspend fun categoryProduct(
        @Path("category") category: String
    ): List<GetCategoryProductResponseItem>

    @GET("products/")
    suspend fun getAllNewProducts(): List<GetAllProductResponseItem>

    @GET("conversations/{userId}")
    suspend fun getAllChat(
        @Path("userId") userId: String
    ): List<ConversationsResponseItem>

    @POST("conversations")
    suspend fun createConversations(
        @Body request: ConversationsRequest
    ): List<ConversationsResponseItem>

    @GET("orders")
    suspend fun getOrders(@Header("Authorization") token: String): Response<List<Order>>

    @Multipart
    @POST("products")
    suspend fun uploadNewProduct(
      @Part("name") name: RequestBody,
      @Part("description") description: RequestBody,
      @Part("price") price: RequestBody,
      @Part("category") category: RequestBody,
      @Part productImage: MultipartBody.Part
    ): UploadNewProductResponse

    @Multipart
    @PUT("products/{productId}")
    suspend fun updateNewProduct(
        @Path("productId") id: String,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("price") price: RequestBody,
        @Part("category") category: RequestBody,
        @Part productImage: MultipartBody.Part
    ): UploadNewProductResponse

    @GET("orders/dashboard")
    suspend fun getDashboardData(): DashboardResponse

    @POST("products/search")
    suspend fun searchProducts(
        @Query("term") term: String
    ): List<SearchProductResponseItem>

    @POST("cart/add")
    suspend fun addItemToCart(
        @Body cartRequest: CartRequest
    ): ApiResponse  // Assuming ApiResponse is your response model
    @GET("users/{userId}")
    suspend fun getSellerProfileByID(
        @Path("userId") userId: String,
    ): Data
}

