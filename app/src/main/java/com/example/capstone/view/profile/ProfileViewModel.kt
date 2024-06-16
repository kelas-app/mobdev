package com.example.capstone.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ProfileViewModel : ViewModel() {
    // Example LiveData for text
    private val _text = MutableLiveData<String>().apply {
        value = "This is Profile Fragment"
    }
    val text: LiveData<String> = _text

    // LiveData for username
    private val _username = MutableLiveData<String>().apply {
        value = "Default Username" // Initial default value
    }
    val username: LiveData<String> = _username

    // Function to update username
    fun updateUsername(newUsername: String) {
        _username.value = newUsername
    }
}