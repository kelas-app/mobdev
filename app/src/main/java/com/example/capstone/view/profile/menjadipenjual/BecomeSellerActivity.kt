package com.example.capstone.view.profile.menjadipenjual

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone.R

class BecomeSellerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_become_seller)

        val btnBack: ImageButton = findViewById(R.id.btnBack)
        val btnSimpan: Button = findViewById(R.id.btnSimpan)
        val putNIK: EditText = findViewById(R.id.PutNIK)

        btnBack.setOnClickListener {
            // Handle back button click
            finish()
        }

        btnSimpan.setOnClickListener {
            val nik = putNIK.text.toString()
            // Handle NIK input (e.g., validate and submit to server)
            if (nik.isNotEmpty()) {
                // Code to handle NIK submission
            } else {
                putNIK.error = getString(R.string.enter_nik)
            }
        }
    }
}
