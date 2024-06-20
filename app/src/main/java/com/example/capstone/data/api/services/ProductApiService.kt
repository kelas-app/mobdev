    package com.example.capstone.data.api.services

    import com.example.capstone.data.api.response.GetAllProductNewResponseItem
    import com.example.capstone.data.api.response.GetAllProductResponseItem
    import com.example.capstone.data.api.response.GetCategoryProductResponse
    import com.example.capstone.data.api.response.GetCategoryProductResponseItem
    import com.example.capstone.data.api.response.GetDetailProductResponse
    import retrofit2.http.Body
    import retrofit2.http.GET
    import retrofit2.http.POST
    import retrofit2.http.Path


    interface ProductApiService {
        @POST("products/recommend")
        suspend fun getAllProducts(
            @Body request: ProductRequest
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


    }