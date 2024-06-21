package com.example.capstone.view.profile.sellerprofile.buyertab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.capstone.R
import com.example.capstone.data.api.response.Data
import com.example.capstone.databinding.FragmentProfileTabBuyerBinding

class BuyerTabFragment : Fragment() {

    private val viewModel: BuyerTabViewModel by viewModels()
    private var _binding: FragmentProfileTabBuyerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileTabBuyerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userData.observe(viewLifecycleOwner, Observer { data ->
            updateUI(data)
        })
    }

    private fun updateUI(data: Data) {
        binding.tvFirstName.text = data.firstname
        binding.tvLastName.text = data.lastname
        binding.tvUsername.text = data.username
        binding.tvPhoneNumber.text = data.phone
        binding.tvAddress.text = data.address
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
