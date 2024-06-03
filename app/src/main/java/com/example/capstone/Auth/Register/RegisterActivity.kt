package com.example.capstone.Auth.Register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone.Auth.Login.LoginActivity
import com.example.capstone.Data.Remote.RegisterRequest
import com.example.capstone.R
import com.example.capstone.Auth.Login.UserViewModel
import androidx.activity.viewModels



class RegisterActivity : AppCompatActivity() {

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText
    private lateinit var etAddress: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvLogin: TextView

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etFirstName = findViewById(R.id.RegFirstname)
        etLastName = findViewById(R.id.RegLastname)
        etUsername = findViewById(R.id.RegUsername)
        etEmail = findViewById(R.id.RegEmail)
        etPhone = findViewById(R.id.RegPhone)
        etPassword = findViewById(R.id.RegPassword)
        etAddress = findViewById(R.id.RegAddress)
        btnRegister = findViewById(R.id.btnRegister)
        tvLogin = findViewById(R.id.tvLogin)

        btnRegister.setOnClickListener {
            val firstName = etFirstName.text.toString().trim()
            val lastName = etLastName.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val address = etAddress.text.toString().trim()

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && username.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty() && address.isNotEmpty()) {
                val registerRequest = RegisterRequest(firstName, lastName, username, email, phone, password, address)
                viewModel.register(registerRequest)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        observeRegisterResponse()
    }

    private fun observeRegisterResponse() {
        viewModel.registerResponse.observe(this) { response ->
            if (response != null) {
                if (response.status == "success") {
                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, "Registration Failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}