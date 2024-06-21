package com.example.capstone.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.api.response.Data
import com.example.capstone.data.api.response.GetDetailProductResponse
import com.example.capstone.data.repository.ProductRepository
import com.example.capstone.data.repository.UserRepository
import kotlinx.coroutines.launch

class DetailProductBaruViewModel(
    private val repository: ProductRepository,
    private val userRepository: UserRepository // Inject UserRepository here
) : ViewModel() {

    private val _productDetails = MutableLiveData<GetDetailProductResponse>()
    val productDetails: LiveData<GetDetailProductResponse> = _productDetails

    private val _sellerProfile = MutableLiveData<Data>()
    val sellerProfile: LiveData<Data> = _sellerProfile

    fun fetchProductDetails(productId: String) {
        viewModelScope.launch {
            repository.getProductDetails(productId).collect { result ->
                result.onSuccess { product ->
                    _productDetails.value = product
                    // Fetch seller profile
                    fetchSellerProfile(product.sellerId) // Assuming sellerId is in GetDetailProductResponse
                }.onFailure {
                    // Handle the error
                }
            }
        }
    }

    private fun fetchSellerProfile(userId: String) {
        viewModelScope.launch {
            try {
                val profile = repository.getSellerProfileByID(userId)
                _sellerProfile.postValue(profile)
            } catch (e: Exception) {
                // Handle error fetching seller profile
            }
        }
    }

}