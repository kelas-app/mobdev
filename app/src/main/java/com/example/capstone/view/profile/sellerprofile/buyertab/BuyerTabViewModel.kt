package com.example.capstone.view.profile.sellerprofile.buyertab

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.capstone.data.api.response.Data
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.pref.dataStore

class BuyerTabViewModel(application: Application) : AndroidViewModel(application) {
    private val userPref: UserPreference = UserPreference.getInstance(application.dataStore)

    val userData: LiveData<Data> = userPref.getSession().asLiveData()
}
