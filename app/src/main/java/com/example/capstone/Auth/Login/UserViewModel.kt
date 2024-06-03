package com.example.capstone.Auth.Login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstone.Data.Remote.ApiResponse
import com.example.capstone.Data.Remote.LoginRequest
import com.example.capstone.Data.Remote.LoginResponse
import com.example.capstone.Data.Remote.RegisterRequest
import com.example.capstone.Data.Remote.RegisterResponse
import com.example.capstone.Data.Remote.UserRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UserRepository()

    private val _registerResponse = MutableLiveData<ApiResponse<RegisterResponse>>()
    val registerResponse: LiveData<ApiResponse<RegisterResponse>> get() = _registerResponse

    private val _loginResponse = MutableLiveData<ApiResponse<LoginResponse>>()
    val loginResponse: LiveData<ApiResponse<LoginResponse>> get() = _loginResponse

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> get() = _error

    fun register(request: RegisterRequest) {
        viewModelScope.launch {
            try {
                _registerResponse.value = repository.register(request)
            } catch (e: Exception) {
                _error.value = e
            }
        }
    }

    fun login(request: LoginRequest) {
        viewModelScope.launch {
            try {
                _loginResponse.value = repository.login(request)
            } catch (e: HttpException) {
                _error.value = e
            } catch (e: Exception) {
                _error.value = e
            }
        }
    }
}

