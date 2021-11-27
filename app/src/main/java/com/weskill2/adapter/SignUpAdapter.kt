package com.weskill2.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.weskill2.ui.authentication.signIn.SignUpFragment

class SignUpAdapter(fragment : SignUpFragment,
                    val list: ArrayList<Fragment>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): androidx.fragment.app.Fragment {
        return list[position]
    }
}