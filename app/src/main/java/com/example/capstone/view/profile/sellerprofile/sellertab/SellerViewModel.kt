package com.example.capstone.view.profile.sellerprofile.sellertab

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.api.response.DashboardResponse
import com.example.capstone.data.api.response.GetDetailProductResponse
import com.example.capstone.data.api.services.ProductApiService
import com.example.capstone.data.repository.ProductRepository
import com.example.capstone.di.Injection
import kotlinx.coroutines.launch

class SellerViewModel(application: Application) : AndroidViewModel(application) {

    private val productRepository: ProductRepository = Injection.provideProductRepository(application)

    private val _dashboardData = MutableLiveData<DashboardResponse>()
    val dashboardData: LiveData<DashboardResponse>
        get() = _dashboardData

    private val _productDetails = MutableLiveData<List<GetDetailProductResponse>>()
    val productDetails: LiveData<List<GetDetailProductResponse>>
        get() = _productDetails

    fun loadDashboardData() {
        viewModelScope.launch {
            try {
                val dashboardResponse = productRepository.getDashboardData()
                _dashboardData.value = dashboardResponse
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("SellerViewModel", "Error fetching dashboard data: ${e.message}")
            }
        }
    }

    fun loadProductDetails(productIds: List<String>) {
        viewModelScope.launch {
            try {
                val details = productIds.map { productId ->
                    productRepository.getProductInfo(productId)
                }
                _productDetails.value = details
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("SellerViewModel", "Error fetching product details: ${e.message}")
            }
        }
    }
}