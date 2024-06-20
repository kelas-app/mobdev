package com.example.capstone.view.profile.editprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.repository.EditProfileRepository

class EditProfileViewModelFactory(
    private val userPreference: UserPreference,
    private val editProfileRepository: EditProfileRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditProfileViewModel::class.java)) {
            return EditProfileViewModel(userPreference, editProfileRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
