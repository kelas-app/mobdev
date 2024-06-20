package com.example.capstone.view.profile.sellerprofile.sellertab

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.api.services.ProductRequest
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.pref.dataStore
import com.example.capstone.di.Injection
import kotlinx.coroutines.launch

class SellerViewModel(application: Application) : AndroidViewModel(application) {
    private val allItems = mutableListOf<ProductRequest>()

    private val productRepository = Injection.provideProductRepository(application.applicationContext)

    private val _sellerItems = MutableLiveData<List<ProductRequest>>()
    private val userPreference = UserPreference.getInstance(application.applicationContext.dataStore)

    val sellerItems: LiveData<List<ProductRequest>>
        get() = _sellerItems

    init {
        // Load initial data
        loadSellerItems()
    }

    private fun loadSellerItems() {
        viewModelScope.launch {
            try {
                val userId = userPreference.getUserId()
                val items = productRepository.getAllProducts()
                val productRequests = items.map { mapToProductRequest(it) }
                val filteredItems = productRequests.filter { it.sellerId == userId }
                allItems.clear()
                allItems.addAll(filteredItems)
                _sellerItems.value = allItems
                Log.d("SellerViewModel", "Fetched and transformed items: $filteredItems")
            } catch (e: Exception) {
                // Handle error fetching data
                e.printStackTrace()
                Log.e("SellerViewModel", "Error fetching data: ${e.message}")
            }
        }
    }

    fun filterItems(showCompleted: Boolean) {
        val filteredItems = allItems.filter { it.isCompleted == showCompleted }
        _sellerItems.value = filteredItems
        Log.d("SellerViewModel", "Filtered items: ${_sellerItems.value}")
    }

    private fun mapToProductRequest(responseItem: GetAllProductResponseItem?): ProductRequest {
        if (responseItem != null) {
            return ProductRequest(
                name = responseItem.name ?: "",
                description = responseItem.description ?: "",
                price = responseItem.price?.toString()?.toFloatOrNull() ?: 0.0f,
                category = responseItem.category ?: "",
                productImage = responseItem.productImage?.filterNotNull() ?: emptyList(),
                isVisible = responseItem.isVisible ?: false,
                isCompleted = responseItem.isCompleted ?: false,
                sellerId = responseItem.sellerId ?: ""
            )
        }
        return ProductRequest(
            name = "",
            description = "",
            price = 0.0f,
            category = "",
            productImage = emptyList(),
            isVisible =false,
            isCompleted = false,
            sellerId = "")
    }
}