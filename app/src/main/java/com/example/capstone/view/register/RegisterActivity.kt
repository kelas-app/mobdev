package com.example.capstone.view.register

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone.databinding.ActivityRegisterBinding
import com.example.capstone.di.factory.AuthViewModelFactory
import com.example.capstone.view.login.LoginActivity
import com.example.capstone.view.main.MainActivity


class RegisterActivity : AppCompatActivity() {

private lateinit var binding : ActivityRegisterBinding

private val viewModel by viewModels<RegisterViewModel>{
    AuthViewModelFactory.getInstance(this)
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnRegister.setOnClickListener{
            showLoading(true)
            val firstname = binding.firstnameEditText.text.toString()
            val lastname = binding.lastnameEditText.text.toString()
            val username = binding.usernameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val address = binding.addressEditText.text.toString()
            viewModel.register(firstname, lastname, username, email, phone, password, address)
            viewModel.registerResult.observe(this){result->
                showLoading(false)
                result.onSuccess {
                    AlertDialog.Builder(this).apply {
                        setTitle("Yeah!")
                        setMessage("Register with $email completed")
                        setPositiveButton("Continue"){_,_ ->
                            val intent = Intent(context, LoginActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()
                        }
                        create()
                        show()
                    }
                }.onFailure {exception ->
                    AlertDialog.Builder(this).apply {
                        Log.e("RegisterActivity", "Registration failed: ${exception.message}")
                        setTitle("Failed")
                        setMessage("Register Failed please try again")
                        setPositiveButton("Try Again"){_,_->
                            val intent = Intent(context, RegisterActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                        create()
                        show()
                    }
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state){
            binding.progressbar.visibility = View.VISIBLE
        }
        else{
            binding.progressbar.visibility = View.GONE

        }

    }
}