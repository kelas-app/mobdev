package com.example.capstone.view.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up your UI components and handle button clicks or other interactions here
        binding.btnSimpan.setOnClickListener {
            // Handle save button click
            // Example: Save the changes made to profile
        }
    }
}
