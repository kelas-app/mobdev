package com.example.capstone.view.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.capstone.R
import com.example.capstone.databinding.FragmentCartBinding
import com.google.android.material.tabs.TabLayoutMediator

class CartFragment : Fragment(){
    private var _binding: FragmentCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        ini untuk memasukkan model nya
        val cartViewModel =
            ViewModelProvider(this)[CartViewModel::class.java]

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        // Setup ViewPager with Adapter
        viewPager.adapter = CartPagerAdapter(this)

        // Connect TabLayout with ViewPager using TabLayoutMediator
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.keranjang)
                1 -> getString(R.string.riwayat_pesanan)
                else -> null
            }
        }.attach()

//        ini km masukin  function sebelum root ya contoh :
//        setUpView()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}