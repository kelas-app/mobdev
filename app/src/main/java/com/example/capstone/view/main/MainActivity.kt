package com.example.capstone.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.capstone.R
import com.example.capstone.databinding.ActivityMainBinding
import com.example.capstone.di.factory.ViewModelFactory
import com.example.capstone.view.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeSession()
        Log.d("MainActivity", "onCreate took ${System.currentTimeMillis() } ms")
    }

    private fun observeSession(){
        viewModel.getSession().observe(this){user->
            Log.d("MainActivity", "Checking session: ${user.token}")
            if (user.token.isEmpty()){
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
            else{
                setupNavigation()
            }
        }
    }

    private fun setupNavigation() {
        val bottomNavigation: BottomNavigationView = binding.bottomNavigation
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        //ini untuk menghubungkan fragment ke dalam activity
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_chat, R.id.nav_cart, R.id.nav_profile
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigation.setupWithNavController(navController)
    }
}