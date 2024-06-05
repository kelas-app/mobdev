package com.example.capstone.view.login

//class LoginActivity : AppCompatActivity() {
//    private lateinit var viewModel: UserViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//
//        val etEmail = findViewById<EditText>(R.id.etEmail)
//        val etPassword = findViewById<EditText>(R.id.etPassword)
//        val btnLogin = findViewById<Button>(R.id.btnLogin)
//        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)
//
//        btnLogin.setOnClickListener {
//            val email = etEmail.text.toString()
//            val password = etPassword.text.toString()
//
//            viewModel.login(LoginRequest(email, password))
//        }
//
//        tvSignUp.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
//
//        observeLoginResponse()
//    }
//
//    private fun observeLoginResponse() {
//        viewModel.loginResponse.observe(this) { response ->
//            if (response != null) {
//                if (response.status == "success") {
//                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        viewModel.error.observe(this) { error ->
//            if (error is HttpException && error.code() == 404) {
//                Toast.makeText(this, "Error 404: Not Found", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "An error occurred: ${error.message}", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}
