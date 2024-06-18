package com.example.capstone.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeViewModel(private val productRepository: ProductRepository, private val userPreference: UserPreference): ViewModel() {

  fun getProducts(userId: String) : LiveData<List<GetAllProductResponseItem>> {
      return productRepository.getProducts(userId).asLiveData()
  }

    fun getUserId(): LiveData<String?> {
        return userPreference.getSession().map { it.id }.asLiveData()
    }
}