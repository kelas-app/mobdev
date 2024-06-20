package com.example.capstone.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.api.response.GetDetailProductResponse
import com.example.capstone.data.repository.ProductRepository
import kotlinx.coroutines.launch

class DetailProductBaruViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _productDetails = MutableLiveData<GetDetailProductResponse>()
    val productDetails: LiveData<GetDetailProductResponse> = _productDetails

    fun fetchProductDetails(productId: String) {
        viewModelScope.launch {
            repository.getProductDetails(productId).collect { result ->
                result.onSuccess { product ->
                    _productDetails.value = product
                }.onFailure {
                    // Handle the error
                }
            }
        }
    }

}