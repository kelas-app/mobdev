package com.example.capstone.view.profile.menjadipenjual

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.R
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.pref.dataStore
import com.example.capstone.di.Injection

class BecomeSellerActivity : AppCompatActivity() {
    private lateinit var becomeSellerViewModel: BecomeSellerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_become_seller)


        val btnBack: ImageButton = findViewById(R.id.btnBack)
        val btnSimpan: Button = findViewById(R.id.btnSimpan)
        val putNIK: EditText = findViewById(R.id.PutNIK)
        val textViewUsernameProfile: TextView = findViewById(R.id.textViewUsernameProfile)

        val userPreference = UserPreference.getInstance(applicationContext.dataStore)
        val editProfileRepository = Injection.provideEditProfileRepository(applicationContext)
        becomeSellerViewModel = ViewModelProvider(
            this,
            BecomeSellerViewModelFactory(userPreference, editProfileRepository)
        )[BecomeSellerViewModel::class.java]

        becomeSellerViewModel.username.observe(this) { username ->
            textViewUsernameProfile.text = username
        }

        becomeSellerViewModel.fetchUserData()

        btnBack.setOnClickListener {
            // Handle back button click
            finish()
        }

        btnSimpan.setOnClickListener {
            val nik = putNIK.text.toString()
            // Handle NIK input (e.g., validate and submit to server)
            if (nik.isNotEmpty()) {
                becomeSellerViewModel.updateToSellerRole(nik)
            } else {
                putNIK.error = getString(R.string.enter_nik)
            }
        }
    }
}
