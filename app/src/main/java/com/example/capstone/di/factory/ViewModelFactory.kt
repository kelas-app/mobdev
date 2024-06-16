package com.example.capstone.di.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.data.repository.UserRepository
import com.example.capstone.di.Injection
import com.example.capstone.view.main.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (private val userRepository: UserRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userRepository) as T
            }

            else -> throw IllegalArgumentException("unknown ViewModel Class: " + modelClass.name)
        }
    }

    companion object {
        fun getInstance(context: Context): ViewModelFactory {
            return ViewModelFactory(Injection.provideUserRepository(context))
        }
    }
}

