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

    private val productRepository = Injection.provideProductRepository(application.applicationContext)

    private val _sellerItems = MutableLiveData<List<ProductRequest>>()

    val sellerItems: LiveData<List<ProductRequest>>
        get() = _sellerItems

    init {
        loadDashboardItems()
    }

    private fun loadDashboardItems() {
        viewModelScope.launch {
            try {
                val dashboardData = productRepository.getDashboardData()
                _sellerItems.value = dashboardData.dijual // Default to showing "dijual"
                Log.d("SellerViewModel", "Fetched dashboard data")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("SellerViewModel", "Error fetching dashboard data: ${e.message}")
            }
        }
    }

    fun showDijualItems() {
        viewModelScope.launch {
            try {
                val dashboardData = productRepository.getDashboardData()
                _sellerItems.value = dashboardData.dijual
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("SellerViewModel", "Error fetching dijual items: ${e.message}")
            }
        }
    }

    fun showDiprosesItems() {
        viewModelScope.launch {
            try {
                val dashboardData = productRepository.getDashboardData()
                _sellerItems.value = dashboardData.diproses.map {
                    ProductRequest(
                        name = "Order ID: ${it._id}",
                        description = "Total Price: ${it.totalPrice}",
                        price = it.totalPrice,
                        category = "Order Status: ${it.status}",
                        productImage = emptyList(),
                        isVisible = true,
                        isCompleted = it.status == "Selesai",
                        sellerId = it.sellerId
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("SellerViewModel", "Error fetching diproses items: ${e.message}")
            }
        }
    }

    fun showSelesaiItems() {
        viewModelScope.launch {
            try {
                val dashboardData = productRepository.getDashboardData()
                _sellerItems.value = dashboardData.selesai
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("SellerViewModel", "Error fetching selesai items: ${e.message}")
            }
        }
    }
}
