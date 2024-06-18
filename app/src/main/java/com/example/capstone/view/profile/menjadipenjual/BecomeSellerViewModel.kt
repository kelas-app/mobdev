package com.example.capstone.view.profile.menjadipenjual

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.repository.EditProfileRepository
import com.example.capstone.view.profile.SellerProfile
import com.example.capstone.view.profile.UserProfile
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class BecomeSellerViewModel(
    private val userPreference: UserPreference,
    private val editProfileRepository: EditProfileRepository
) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    fun fetchUserData() {
        viewModelScope.launch {
            userPreference.getSession().collect { data ->
                _username.value = data.username
            }
        }
    }

    fun updateToSellerRole(nik: String) {
        viewModelScope.launch {
            userPreference.getSession().firstOrNull()?.let { userData ->
                val userId = userData.id
                val updatedSellerProfile = SellerProfile("seller",nik)
                if (userId != null) {
                    editProfileRepository.updateSellerProfile(userId, updatedSellerProfile)
                }

                // Update UserPreference with the new role and nik
                userPreference.saveSession(userData.copy(role = "seller", nik = nik))
            }
        }
    }

}
