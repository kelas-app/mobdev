    package com.example.capstone.data.api.services

    import com.example.capstone.data.api.response.GetAllProductResponse
    import com.example.capstone.data.api.response.GetAllProductResponseItem
    import com.example.capstone.data.api.response.LoginResponse
    import com.example.capstone.data.api.response.UploadNewProductResponse
    import retrofit2.http.Body
    import retrofit2.http.GET
    import retrofit2.http.POST
    import retrofit2.http.Query

    interface ProductApiService {
    //    @GET("products/recommend")
    //    suspend fun getAllProducts():GetAllProductResponse
    //@GET("products/recommend")
    //suspend fun getAllProducts(
    //    @Query("userId") userId: String
    //): GetAllProductResponse

        @POST("products/recommend")
        suspend fun getAllProducts(
            @Body request: ProductRequest
        ): List<GetAllProductResponseItem>

    }