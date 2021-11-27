package com.weskill2.ui.components.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.weskill2.ui.fragments.ContestsFragment
import com.weskill2.ui.fragments.FeedFragment
import com.weskill2.ui.fragments.StageFragment

class FragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FeedFragment()
            }
            1 -> {
                StageFragment()
            }
            else -> {
                ContestsFragment()
            }
        }
    }
}