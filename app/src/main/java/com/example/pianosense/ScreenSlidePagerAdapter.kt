package com.example.pianosense

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ScreenSlidePagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3  // Fragment sayısı (üç fragmentimiz var)

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Screen1Fragment()  // İlk fragment
            1 -> Screen2Fragment()  // İkinci fragment
            2 -> Screen3Fragment()  // Üçüncü fragment
            else -> Screen1Fragment()  // Varsayılan fragment (her ihtimale karşı)
        }
    }
}
