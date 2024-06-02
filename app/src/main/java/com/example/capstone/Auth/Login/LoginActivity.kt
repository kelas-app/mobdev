package com.example.capstone.Auth.Login
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.Data.Remote.LoginRequest
import com.example.capstone.MainActivity
import com.example.capstone.R

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            viewModel.login(LoginRequest(email, password))
        }

        observeLoginResponse()
    }

    private fun observeLoginResponse() {
        viewModel.loginResponse.observe(this) { response ->
            if (response != null) {
                if (response.status == "success") {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
