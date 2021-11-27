package com.weskill2.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.weskill2.databinding.FragmentCommunityBinding
import com.weskill2.ui.components.adapters.FragmentAdapter

class CommunityFragment(private val tabIndexToBeOpened: Int = 0) : Fragment() {

    private lateinit var b: FragmentCommunityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        b = FragmentCommunityBinding.inflate(layoutInflater, container, false)

        setupTabs()

        return b.root

    }

    private fun setupTabs() {

        b.communityPager.apply {
            adapter = FragmentAdapter(this@CommunityFragment)
            currentItem = tabIndexToBeOpened
        }
        TabLayoutMediator(b.communityTabs, b.communityPager) { tab, pos ->
            tab.text = when (pos) {
                0 -> {
                    "FEED"
                }
                1 -> {
                    "STAGE"
                }
                else -> {
                    "CONTESTS"
                }
            }
        }.attach()
    }

}
