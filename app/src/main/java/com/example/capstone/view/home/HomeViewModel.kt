package com.example.capstone.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstone.data.api.response.GetAllProductNewResponseItem
import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.api.response.GetCategoryProductResponseItem
import com.example.capstone.data.api.response.SearchProductResponseItem
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.repository.ProductRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class HomeViewModel(private val productRepository: ProductRepository, private val userPreference: UserPreference): ViewModel() {

    private val _products = MutableLiveData<Result<List<GetAllProductResponseItem>>>()
    val products: LiveData<Result<List<GetAllProductResponseItem>>> = _products

    private val _categories = MutableLiveData<Result<List<GetCategoryProductResponseItem>>>()
    val categories: LiveData<Result<List<GetCategoryProductResponseItem>>> = _categories

    private val _allProducts = MutableLiveData<Result<List<GetAllProductNewResponseItem>>>()
    val allProducts: LiveData<Result<List<GetAllProductNewResponseItem>>> = _allProducts

    private val _searchResults = MutableLiveData<Result<List<SearchProductResponseItem>>>()
    val searchResults: LiveData<Result<List<SearchProductResponseItem>>> = _searchResults




    fun getProducts(userId: String): LiveData<Result<List<GetAllProductResponseItem>>> {
        val resultFlow = productRepository.getProducts(userId)
            .onStart { /* Show loading */ }
            .catch { exception -> _products.postValue(Result.failure(exception)) }

        return resultFlow.asLiveData()
    }

    fun getCategories(category: String): LiveData<Result<List<GetCategoryProductResponseItem>>> {
        val resultFlow = productRepository.getProductCategory(category)
            .onStart { /* Show loading */ }
            .catch { exception -> _categories.postValue(Result.failure(exception)) }

        return resultFlow.asLiveData()
    }

    fun getAllNewProducts(): LiveData<Result<List<GetAllProductNewResponseItem>>>{
        val resultFlow = productRepository.getAllNewProduct()
            .onStart {  }
            .catch { exception -> _allProducts.postValue(Result.failure(exception)) }

        return resultFlow.asLiveData()
    }

    fun searchProducts(term: String): LiveData<Result<List<SearchProductResponseItem>>> {
        val resultFlow = productRepository.searchProducts(term)
            .onStart { /* Show loading */ }
            .catch { exception -> _searchResults.postValue(Result.failure(exception)) }

        return resultFlow.asLiveData()
    }

    fun getUserId(): LiveData<String?> {
        return userPreference.getSession().map { it.id }.asLiveData()
    }



}