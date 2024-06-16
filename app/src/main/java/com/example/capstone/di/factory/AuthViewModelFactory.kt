package com.example.capstone.di.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.data.repository.UserRepository
import com.example.capstone.di.Injection
import com.example.capstone.view.login.LoginViewModel
import com.example.capstone.view.register.RegisterViewModel
import java.lang.IllegalArgumentException

class AuthViewModelFactory(private val repository: UserRepository):
    ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(LoginViewModel::class.java) ->{
                LoginViewModel(repository) as T
            }

            modelClass.isAssignableFrom(RegisterViewModel::class.java) ->{
                RegisterViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("unknown ViewModel Class: "+ modelClass.name)
        }
    }

    companion object{
        fun getInstance(context: Context) = AuthViewModelFactory(Injection.provideUserRepository(context))
    }
}