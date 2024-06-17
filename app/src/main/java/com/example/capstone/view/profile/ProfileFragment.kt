package com.example.capstone.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.pref.dataStore
import com.example.capstone.databinding.FragmentProfileBinding
import com.example.capstone.view.profile.editprofile.EditProfileActivity

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val userPreference = UserPreference.getInstance(requireContext().dataStore)

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
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        binding.btnMenjadiPenjual.setOnClickListener {
            // Handle the click event for becoming a seller button
        }

        binding.btnBukaMaps.setOnClickListener {
            // Handle the click event for opening Google Maps button
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}