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
import androidx.navigation.fragment.findNavController
import com.example.capstone.R
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.pref.dataStore
import com.example.capstone.databinding.FragmentProfileBinding
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userPreference = UserPreference.getInstance(requireContext().dataStore)

        val profileViewModel = ViewModelProvider(this, ProfileViewModelFactory(userPreference)).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        profileViewModel.username.observe(viewLifecycleOwner) { username ->
            binding.textViewUsername.text = username
        }

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileViewModel = ViewModelProvider(this, ProfileViewModelFactory(userPreference)).get(ProfileViewModel::class.java)

        profileViewModel.role.observe(viewLifecycleOwner) { role ->
            if (role == "seller") {
                // Navigate to SellerProfileFragment if user is seller
                //navigateToSellerProfile()
            }
        }

        profileViewModel.fetchUsername()
        profileViewModel.fetchUserRole()
        setUpView()
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
                    logout()
                }
            }
        }
        builder.show()
    }
    private fun logout() {
        lifecycleScope.launch {
            userPreference.logout()
        }
    }
    private fun navigateToSellerProfile() {
        // Navigasi menggunakan findNavController
        //findNavController().navigate(R.id.action_nav_profile_to_nav_seller_profile)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}