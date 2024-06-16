package com.example.capstone.view.login

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
import com.example.capstone.R
import com.example.capstone.databinding.ActivityLoginBinding
import com.example.capstone.di.factory.AuthViewModelFactory
import com.example.capstone.view.main.MainActivity
import com.example.capstone.view.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel> {
        AuthViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("LoginActivity", "onCreate took ${System.currentTimeMillis()} ms")

        binding.registerBtn.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        setUpView()
        setUpAction()

    }

    private fun setUpView() {
       @Suppress("DEPRECATION")
       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
           window.insetsController?.hide(WindowInsets.Type.statusBars())
       }
        else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
       }
        supportActionBar?.hide()
    }

    private fun setUpAction() {
      binding.btnLogin.setOnClickListener{
        showLoading(true)
          val username = binding.nameEditText.text.toString()
          val password = binding.passwordEditText.text.toString()
          viewModel.login(username,password)
          viewModel.data.observe(this){result ->
              showLoading(false)
              result.onSuccess {
                  AlertDialog.Builder(this).apply {
                      setTitle("Yeah!")
                      setMessage(getString(R.string.login_berhasil))
                      setPositiveButton("Lanjut"){_, _, ->
                          val intent = Intent(context, MainActivity::class.java)
                          startActivity(intent)
                          finish()
                      }
                      create()
                      show()
                  }
              }.onFailure { exception ->
                  AlertDialog.Builder(this).apply {
                      setTitle("Gagal")
                      setMessage("Login Gagal Coba Lagi")
                      setPositiveButton("Coba Lagi"){_, _ ->
                          val intent = Intent(context, LoginActivity::class.java)
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
        if(state){
            binding.progressbar.visibility = View.VISIBLE
        }
        else{
            binding.progressbar.visibility = View.GONE
        }

    }

}
