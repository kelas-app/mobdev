package com.example.capstone.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.api.response.Data
import kotlinx.coroutines.launch
import com.example.capstone.data.pref.UserPreference



class ProfileViewModel(private val userPreference: UserPreference) : ViewModel() {
    // LiveData for username
    private val _username = MutableLiveData<String>().apply {
        value = "default"
    }
    val username: LiveData<String> = _username

    private val _role = MutableLiveData<String>().apply {
        value = "buyer"
    }
    val role: LiveData<String> = _role
    val userData: LiveData<Data> = userPreference.getSession().asLiveData()


    // Function to fetch username from API using stored ID
    fun fetchUsername() {
        viewModelScope.launch {
            userPreference.getSession().collect { data ->
                _username.value = data.username
            }
        }
    }
    fun fetchUserRole() {
        viewModelScope.launch {
            userPreference.getSession().collect { data ->
                _role.value = data.role
            }
        }
    }

    // Function to update username
    fun updateUsername(newUsername: String) {
        _username.value = newUsername
    }
}