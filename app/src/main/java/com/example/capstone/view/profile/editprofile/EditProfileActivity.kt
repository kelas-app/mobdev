    package com.example.capstone.view.profile.editprofile

    import android.os.Bundle
    import android.util.Log
    import androidx.appcompat.app.AppCompatActivity
    import androidx.lifecycle.ViewModelProvider
    import com.example.capstone.data.pref.UserPreference
    import com.example.capstone.data.pref.dataStore
    import com.example.capstone.databinding.ActivityEditProfileBinding
    import com.example.capstone.di.Injection
    import kotlinx.coroutines.CoroutineScope
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.launch
    import kotlinx.coroutines.withContext


    class EditProfileActivity : AppCompatActivity() {

        private lateinit var binding: ActivityEditProfileBinding
        private lateinit var editProfileViewModel: EditProfileViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityEditProfileBinding.inflate(layoutInflater)
            setContentView(binding.root)
            val btnBack = binding.btnBack
            val editProfileRepository = Injection.provideEditProfileRepository(applicationContext)
            val userPreference = UserPreference.getInstance(applicationContext.dataStore)
            editProfileViewModel = ViewModelProvider(this, EditProfileViewModelFactory(userPreference,editProfileRepository)).get(EditProfileViewModel::class.java)



            // Observe the username LiveData
            editProfileViewModel.username.observe(this) { username ->
                binding.textViewUsernameProfile.text = username
            }
            editProfileViewModel.updateStatus.observe(this) { isSuccess ->
                if (isSuccess) {
                    Log.d("EditProfileActivity", "Profile updated successfully")
                    // Handle successful update, e.g., show a success message
                } else {
                    Log.d("EditProfileActivity", "Profile update failed")
                    // Handle update failure, e.g., show an error message
                }
            }
            // Fetch the username from UserPreference
            editProfileViewModel.fetchUserData()
            btnBack.setOnClickListener { onBackPressed() }


            // Set up your UI components and handle button clicks or other interactions here
            binding.btnSimpan.setOnClickListener {
                val newFirstName = binding.PutFirstName.text.toString()
                val newLastName = binding.PutLastName.text.toString()
                val newUsername = binding.PutUsername.text.toString()
                val newPhone = binding.PutPhone.text.toString()
                val newAddress = binding.PutAddress.text.toString()


                Log.d("EditProfileActivity", "Save button clicked: $newFirstName, $newLastName, $newUsername, $newPhone, $newAddress")

                CoroutineScope(Dispatchers.Main).launch {
                    val userId = withContext(Dispatchers.IO) {
                        userPreference.getUserId()
                    }
                    Log.d("EditProfileActivity", "Fetched User ID: $userId")

                    if (userId.isNotEmpty()) {
                        editProfileViewModel.updateUserProfile(userId, newFirstName, newLastName, newUsername,newPhone,newAddress)
                    } else {
                        Log.d("EditProfileActivity", "User ID is missing")
                    }
                }
            }
        }
    }
