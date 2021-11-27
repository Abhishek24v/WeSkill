package com.weskill2.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.weskill2.ui.fragments.OnBoardingFragment

class VPObAdapter(fragment : OnBoardingFragment,
                  val list: ArrayList<Fragment>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): androidx.fragment.app.Fragment {
        return list[position]
    }
}