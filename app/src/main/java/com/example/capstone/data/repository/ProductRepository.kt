package com.example.capstone.data.repository

import com.example.capstone.data.api.response.CartItem
import com.example.capstone.data.api.response.ConversationsResponseItem
import com.example.capstone.data.api.response.DashboardResponse
import com.example.capstone.data.api.response.GetAllProductNewResponseItem
import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.api.response.GetCategoryProductResponseItem
import com.example.capstone.data.api.response.GetDetailProductResponse
import com.example.capstone.data.api.response.SearchProductResponseItem
import com.example.capstone.data.api.response.UploadNewProductResponse
import com.example.capstone.data.api.services.ConversationsRequest
import com.example.capstone.data.api.services.ProductApiService
import com.example.capstone.data.api.services.ProductRequestRecommend
import com.example.capstone.data.pref.UserPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ProductRepository private constructor(
    private val productApiService: ProductApiService,
    val userPreference: UserPreference,){
    companion object{
        @Volatile
        private var instance: ProductRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            productApiService: ProductApiService
        ) = ProductRepository(productApiService, userPreference)
    }
       
    suspend fun getOrders(token: String) = productApiService.getOrders(token)


    suspend fun getCartItems(): List<CartItem> = withContext(Dispatchers.IO) {
        productApiService.getCartItems()
    }

    fun getProductRecommendation(userId: String): Flow<Result<List<GetAllProductResponseItem>>> = flow {
        if (userPreference.isTokenExpired()) {
            userPreference.logout()
            emit(Result.failure(Exception("Token expired")))
        } else {
            try {
                val products = productApiService.getAllProducts(ProductRequestRecommend(userId))
                emit(Result.success(products))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }
    suspend fun getProductInfo(productId: String): GetDetailProductResponse {
        return productApiService.detailProduct(productId)
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

    fun getAllProduct(): Flow<Result<List<GetAllProductResponseItem>>> = flow {
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
    
    suspend fun uploadNewProduct(name: String, description: String, price: Float, category: String, productImage: File): UploadNewProductResponse {
        val namePart = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
        val descriptionPart = RequestBody.create("text/plain".toMediaTypeOrNull(), description)
        val pricePart = RequestBody.create("text/plain".toMediaTypeOrNull(), price.toString())
        val categoryPart = RequestBody.create("text/plain".toMediaTypeOrNull(), category)
        val imagePart = MultipartBody.Part.createFormData("productImage", productImage.name, RequestBody.create("image/*".toMediaTypeOrNull(), productImage))

        return productApiService.uploadNewProduct(namePart, descriptionPart, pricePart, categoryPart, imagePart)
    }
    suspend fun editNewProduct(id: String,name: String, description: String, price: Float, category: String, productImage: File): UploadNewProductResponse {
        val namePart = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
        val descriptionPart = RequestBody.create("text/plain".toMediaTypeOrNull(), description)
        val pricePart = RequestBody.create("text/plain".toMediaTypeOrNull(), price.toString())
        val categoryPart = RequestBody.create("text/plain".toMediaTypeOrNull(), category)
        val imagePart = MultipartBody.Part.createFormData("productImage", productImage.name, RequestBody.create("image/*".toMediaTypeOrNull(), productImage))

        // Get productId from somewhere, possibly from ViewModel or Activity
        val productId = id

        return productApiService.updateNewProduct(productId, namePart, descriptionPart, pricePart, categoryPart, imagePart)
    }
    fun getAllChat (productId:String): Flow<Result<List<ConversationsResponseItem>>> = flow {
        if (userPreference.isTokenExpired()) {
            logout()
            emit(Result.failure(Exception("Token expired")))
        } else{
            try {
                val chat = productApiService.getAllChat(productId)
                emit(Result.success(chat))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

    fun createConversation(request: ConversationsRequest): Flow<Result<List<ConversationsResponseItem>>> = flow {
        if (userPreference.isTokenExpired()) {
            logout()
            emit(Result.failure(Exception("Token expired")))
        } else {
            try {
                val conversation = productApiService.createConversations(request)
                emit(Result.success(conversation))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

    fun searchProducts(term: String): Flow<Result<List<SearchProductResponseItem>>> = flow {
        if (userPreference.isTokenExpired()) {
            logout()
            emit(Result.failure(Exception("Token expired")))
        } else {
            try {
                val searchResults = productApiService.searchProducts(term)
                emit(Result.success(searchResults))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

    suspend fun getDashboardData(): DashboardResponse {
        return productApiService.getDashboardData()
    }

    suspend fun logout(){
        userPreference.logout()
    }
}