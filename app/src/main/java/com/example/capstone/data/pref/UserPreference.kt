package com.example.capstone.data.pref

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.capstone.data.api.response.Data
import kotlinx.coroutines.flow.first
import com.example.capstone.data.api.services.AuthApiService
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow
import java.util.prefs.Preferences


val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "session")

class UserPreference private constructor(private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>) {

    suspend fun saveSession(data: Data) {
        dataStore.edit { preferences ->
            preferences[ID_KEY] = data.id?:""
            preferences[FIRSTNAME_KEY] = data.firstname?:""
            preferences[LASTNAME_KEY] = data.lastname?:""
            preferences[USERNAME_KEY] = data.username?:""
            preferences[EMAIL_KEY] = data.email?:""
            preferences[PHONE_KEY] = data.phone?:""
            preferences[ADDRESS_KEY] = data.address?:""
            preferences[TOKEN_KEY] = data.token
            preferences[EXPIRATION_TOKEN_KEY] = System.currentTimeMillis() + 60 * 60 * 1000
            preferences[ROLE] = data.role
            preferences[NIK] = data.nik

            Log.d("UserPreference", "Saved Preferences")
            Log.d("UserPreference", "Saved Preferences: ${data.id}")

        }
    }
    suspend fun updateUser(data: Data) {
        dataStore.edit { preferences ->
            preferences[ID_KEY] = data.id ?: ""
            preferences[FIRSTNAME_KEY] = data.firstname ?: ""
            preferences[LASTNAME_KEY] = data.lastname ?: ""
            preferences[USERNAME_KEY] = data.username ?: ""
            preferences[EMAIL_KEY] = data.email ?: ""
            preferences[PHONE_KEY] = data.phone ?: ""
            preferences[ADDRESS_KEY] = data.address ?: ""
            preferences[ROLE] = data.role
            preferences[NIK] = data.nik

            Log.d("UserPreference", "Updated Preferences")
        }
    }
    fun getSession(): kotlinx.coroutines.flow.Flow<Data> {
        return dataStore.data.map { preferences ->
            Data(
                id = preferences[ID_KEY] ?: "",
                firstname = preferences[FIRSTNAME_KEY] ?:"",
                lastname = preferences[LASTNAME_KEY] ?:"" ,
                username = preferences[USERNAME_KEY]?:"",
                email = preferences[EMAIL_KEY]?:"",
                phone = preferences[PHONE_KEY]?:"",
                address = preferences[ADDRESS_KEY]?:"",
                token = preferences[TOKEN_KEY] ?: "",
                role = preferences[ROLE] ?: "",
                nik = preferences[NIK] ?: ""

            )
        }
    }
    suspend fun getUserId(): String {
        return dataStore.data.map { preferences ->
            preferences[ID_KEY] ?: ""
        }.firstOrNull() ?: ""
    }

    suspend fun logout(){
        dataStore.edit { preferences ->
            preferences.clear()
            Log.d("UserPreference", "Clearing Preferences")
        }
    }

    suspend fun isTokenExpired():Boolean {
        val currentTime = System.currentTimeMillis()
        val expirationTime = dataStore.data.map { preferences ->
            preferences[EXPIRATION_TOKEN_KEY] ?: 0L
        }.first() // collect from flow value
        return currentTime > expirationTime
    }


    companion object{
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val ID_KEY = stringPreferencesKey("userId")
        private val FIRSTNAME_KEY = stringPreferencesKey("firstname")
        private val LASTNAME_KEY = stringPreferencesKey("lastname")
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val PHONE_KEY = stringPreferencesKey("phone")
        private val ADDRESS_KEY = stringPreferencesKey("address")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val EXPIRATION_TOKEN_KEY = longPreferencesKey("expiration_time")
        private val ROLE = stringPreferencesKey("role")
        private val NIK = stringPreferencesKey("nik")

        fun getInstance(dataStore: DataStore<androidx.datastore.preferences.core.Preferences>): UserPreference{
            return INSTANCE ?: synchronized(this){
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}