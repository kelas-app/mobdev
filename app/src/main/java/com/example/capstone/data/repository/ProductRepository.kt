package com.example.capstone.data.repository

import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.api.services.ProductApiService
import com.example.capstone.data.pref.UserPreference
import kotlinx.coroutines.flow.flow

class ProductRepository private constructor(
    private val productApiService: ProductApiService,
    private val userPreference: UserPreference,){
    companion object{
        @Volatile
        private var instance: ProductRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            productApiService: ProductApiService
        ) = ProductRepository(productApiService, userPreference)
    }

    fun getProducts(): kotlinx.coroutines.flow.Flow<List<GetAllProductResponseItem>> = flow{
        val  products = productApiService.getAllProducts().getAllProductResponse
        emit(products)
    }
}