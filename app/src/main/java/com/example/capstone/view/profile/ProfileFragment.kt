package com.example.capstone.view.profile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.pref.dataStore
import com.example.capstone.databinding.FragmentProfileBinding
import com.example.capstone.view.login.LoginActivity
import com.example.capstone.view.profile.editprofile.EditProfileActivity
import com.example.capstone.view.profile.menjadipenjual.BecomeSellerActivity
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var userPreference: UserPreference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userPreference = UserPreference.getInstance(requireContext().dataStore)

//        ini untuk memasukkan model nya
        val profileViewModel = ViewModelProvider(this, ProfileViewModelFactory(userPreference)).get(ProfileViewModel::class.java)


        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        profileViewModel.username.observe(viewLifecycleOwner) { username ->
            binding.textViewUsername.text = username
        }

//        ini km masukin  function sebelum root ya contoh :
        profileViewModel.fetchUsername()
        setUpView()
        return root
    }

    private fun setUpView() {
        // Setup your view components here
        binding.ibSetting.setOnClickListener {
            showProfileOptionsDialog()
        }

        binding.btnMenjadiPenjual.setOnClickListener {
            val intent = Intent(requireContext(), BecomeSellerActivity::class.java)
            startActivity(intent)
        }

        binding.btnBukaMaps.setOnClickListener {
            // Handle the click event for opening Google Maps button
        }
    }
    private fun showProfileOptionsDialog() {
        val options = arrayOf("Edit Profile", "Logout")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Settings")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> {
                    val intent = Intent(requireContext(), EditProfileActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                    // Handle logout logic here
                    logout()
                }
            }
        }
        builder.show()
    }
    private fun logout() {
        lifecycleScope.launch {
            userPreference.logout()
            // Redirect to login screen after logout
            /*val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)*/
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}