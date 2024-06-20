package com.example.capstone.data.repository

import com.example.capstone.data.api.response.GetAllProductNewResponseItem
import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.api.response.GetCategoryProductResponse
import com.example.capstone.data.api.response.GetCategoryProductResponseItem
import com.example.capstone.data.api.response.GetDetailProductResponse
import com.example.capstone.data.api.services.ProductApiService
import com.example.capstone.data.api.services.ProductRequest
import com.example.capstone.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow
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

    fun getProducts(userId: String): kotlinx.coroutines.flow.Flow<Result<List<GetAllProductResponseItem>>> = flow {
        if (userPreference.isTokenExpired()) {
            userPreference.logout()
            emit(Result.failure(Exception("Token expired")))
        } else {
            try {
                val products = productApiService.getAllProducts(ProductRequest(userId))
                emit(Result.success(products))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

    fun getProductDetails(productId:String): Flow<Result<GetDetailProductResponse>> = flow {
        if (userPreference.isTokenExpired()) {
            logout()
            emit(Result.failure(Exception("Token expired")))
        } else{
            try {
                val productDetails = productApiService.detailProduct(productId)
                emit(Result.success(productDetails))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

    fun getProductCategory(category:String): Flow<Result<List<GetCategoryProductResponseItem>>> = flow {
        if (userPreference.isTokenExpired()) {
            logout()
            emit(Result.failure(Exception("Token expired")))
        } else{
            try {
                val productCategory = productApiService.categoryProduct(category)
                emit(Result.success(productCategory))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

    fun getAllNewProduct(): Flow<Result<List<GetAllProductNewResponseItem>>> = flow {
        if (userPreference.isTokenExpired()) {
            logout()
            emit(Result.failure(Exception("Token expired")))
        } else{
            try {
                val allProduct = productApiService.getAllNewProducts()
                emit(Result.success(allProduct))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

    suspend fun logout(){
        userPreference.logout()
    }


}