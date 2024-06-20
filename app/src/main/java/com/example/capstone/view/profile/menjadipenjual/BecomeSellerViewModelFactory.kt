package com.example.capstone.view.profile.menjadipenjual

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.repository.EditProfileRepository

class BecomeSellerViewModelFactory(
    private val userPreference: UserPreference,
    private val editProfileRepository: EditProfileRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BecomeSellerViewModel(userPreference, editProfileRepository) as T
    }
}
