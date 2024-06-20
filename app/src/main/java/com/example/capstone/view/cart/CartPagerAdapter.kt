package com.example.capstone.view.cart

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.capstone.view.cart.keranjang.KeranjangFragment
import com.example.capstone.view.cart.riwayat.RiwayatPesananFragment

class CartPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2 // Jumlah fragment yang akan ditampilkan

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> KeranjangFragment()
            1 -> RiwayatPesananFragment() // Gantikan dengan fragment riwayat pesanan jika ada
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}
