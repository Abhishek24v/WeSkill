package com.weskill2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.weskill2.ui.authentication.signIn.ChooseParentFragment
import com.weskill2.ui.authentication.signIn.ParentInfoFragment

class SignUpProgressAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager,lifecycle) {

    private val noOfTabs = 3
    override fun getItemCount(): Int {
        return noOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        /*when (position) {
            0 -> return SignUpDetailsFragment()
            1 -> return ChooseParentFragment()
            2 -> return ParentInfoFragment()
        }
        return SignUpDetailsFragment()*/
        return ChooseParentFragment()
    }
}