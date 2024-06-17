package com.example.capstone.view.profile.editprofile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.view.profile.UserProfile
import com.example.capstone.data.api.response.UserProfileResponse
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.repository.EditProfileRepository
import com.example.capstone.data.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class EditProfileViewModel(
    private val userPreference: UserPreference,
    private val editProfileRepository: EditProfileRepository
) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> get() = _userId

    private val _updateStatus = MutableLiveData<Boolean>()
    val updateStatus: LiveData<Boolean> get() = _updateStatus
    // Function to fetch username and email from UserPreference
    fun fetchUserData() {
        viewModelScope.launch {
            userPreference.getSession().collect { data ->
                _username.value = data.username ?: ""
                _email.value = data.email ?: ""
                _userId.value = data.id ?: ""
                Log.d("EditProfileViewModel", "Fetched user data: $data")
            }
        }
    }

    fun updateUserProfile(userId: String, newUsername: String, newEmail: String, newPassword: String) {
        viewModelScope.launch {
            try {
                val userProfile = UserProfile(newUsername, newEmail, newPassword)
                editProfileRepository.updateUserProfile(userId, userProfile)
                _updateStatus.value = true // If successful without checking isSuccessful
                Log.d("EditProfileViewModel", "Update profile successful")

            } catch (e: Exception) {
                _updateStatus.value = false
                Log.e("EditProfileViewModel", "Update profile failed", e)
            }
        }
    }
}
