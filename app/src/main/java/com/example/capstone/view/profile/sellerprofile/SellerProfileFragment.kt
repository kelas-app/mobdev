package com.example.capstone.view.profile.sellerprofile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.capstone.R
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.pref.dataStore
import com.example.capstone.databinding.FragmentProfileSellerBinding
import com.example.capstone.view.profile.ProfileViewModel
import com.example.capstone.view.profile.ProfileViewModelFactory
import com.example.capstone.view.profile.editprofile.EditProfileActivity
import com.example.capstone.view.profile.sellerprofile.buyertab.BuyerTabFragment
import com.example.capstone.view.profile.sellerprofile.sellertab.SellerTabFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class SellerProfileFragment : Fragment() {
    private var _binding: FragmentProfileSellerBinding? = null
    private val binding get() = _binding!!

    private lateinit var userPreference: UserPreference

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileSellerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        userPreference = UserPreference.getInstance(requireContext().dataStore)
        val profileViewModel = ViewModelProvider(this, ProfileViewModelFactory(userPreference)).get(ProfileViewModel::class.java)

        profileViewModel.username.observe(viewLifecycleOwner) { username ->
            binding.textViewSellerUsername.text = username
        }

        profileViewModel.fetchUsername()
        setUpView()

        return root
    }

    private fun setUpView() {
        // Setup your view components here for seller profile
        binding.ibSetting.setOnClickListener {
            showProfileOptionsDialog()
        }

        // Add more setup for seller profile UI components
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = binding.viewPager
        tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.buyer_tab)
                1 -> tab.text = getString(R.string.seller_tab)
            }
        }.attach()
    }
    private inner class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

        override fun getItemCount(): Int = 2 // Number of tabs

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> BuyerTabFragment() // Replace with your actual fragment for chart
                1 -> SellerTabFragment() // Replace with your actual fragment for history
                else -> throw IllegalArgumentException("Invalid position")
            }
        }
    }
    private fun logout() {
        lifecycleScope.launch {
            userPreference.logout()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
