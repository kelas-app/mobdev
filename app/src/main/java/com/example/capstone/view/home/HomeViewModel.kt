package com.example.capstone.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.repository.ProductRepository

class HomeViewModel(private val productRepository: ProductRepository): ViewModel() {

  fun getStories() : LiveData<List<GetAllProductResponseItem>> {
      return productRepository.getProducts().asLiveData()
  }
}