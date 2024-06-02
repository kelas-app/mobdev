package com.example.capstone.Auth.Register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone.Auth.Login.LoginActivity
import com.example.capstone.R
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException


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
                registerUser(firstName, lastName, username, email, phone, password, address)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser(firstName: String, lastName: String, username: String, email: String, phone: String, password: String, address: String) {
        val client = OkHttpClient()
        val url = "http://161.97.109.65:3000/auth/register"

        val json = JSONObject()
        json.put("firstname", firstName)
        json.put("lastname", lastName)
        json.put("username", username)
        json.put("email", email)
        json.put("phone", phone)
        json.put("password", password)
        json.put("address", address)

        val body = json.toString().toRequestBody("application/json; charset=utf-8".toMediaType())

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@RegisterActivity, "Registration Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    val jsonResponse = responseData?.let { JSONObject(it) }
                    val status = jsonResponse?.getString("status")

                    if (status == "success") {
                        runOnUiThread {
                            Toast.makeText(this@RegisterActivity, "Registration Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        val message = jsonResponse?.getString("message")
                        runOnUiThread {
                            Toast.makeText(this@RegisterActivity, "Registration Failed: $message", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@RegisterActivity, "Registration Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}