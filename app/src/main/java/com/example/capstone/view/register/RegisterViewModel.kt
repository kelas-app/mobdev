package com.example.capstone.view.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.api.response.RegisterResponse
import com.example.capstone.data.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _registerResult = MutableLiveData<Result<RegisterResponse>>()
    val registerResult : LiveData<Result<RegisterResponse>> = _registerResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

     fun register(firstname:String, lastname:String, username:String, email : String, phone:String, password: String, address:String){
       viewModelScope.launch {
           try {
               val response = userRepository.register(firstname, lastname, username, email, phone, password, address)
               _registerResult.value = Result.success(response)
           }
           catch (e: Exception){
               _errorMessage.value = e.message ?: "An error occurred"
               _registerResult.value = Result.failure(e)
           }
       }
    }
}