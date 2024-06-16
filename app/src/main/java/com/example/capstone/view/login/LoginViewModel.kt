package com.example.capstone.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.api.response.Data
import com.example.capstone.data.api.response.LoginResponse
import com.example.capstone.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel (private val repository: UserRepository) : ViewModel(){

    private val _data = MutableLiveData<Result<LoginResponse>>()
    val data: LiveData<Result<LoginResponse>> = _data

    private val _sessionResult = MutableLiveData<Data?>()
    val sessionResult : LiveData<Data?> = _sessionResult

    fun login(email:String, password:String){
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                _data.value = Result.success(response)
                val user = response.data
                if(user != null){
                   repository.saveSession(user)
                   _sessionResult.value = user
                   Log.d("LoginViewModel", "Token Saved and service updated: ${user.token}")
                }
            }
            catch (e : Exception){
                _data.value = Result.failure(e)
            }
        }
    }

}

