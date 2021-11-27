package com.weskill2.ui.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.weskill2.R
import com.weskill2.databinding.FragmentCompetitionInfoBinding
import com.weskill2.ui.activities.EntryDoneDialog

class CompetitionInfoFragment : Fragment() {

    private lateinit var b: FragmentCompetitionInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentCompetitionInfoBinding.inflate(layoutInflater, container, false)

        setClicks()

        return b.root
    }

    private fun setClicks() {
        b.enrollNow.setOnClickListener {
            EntryDoneDialog(activity as Activity).show()
        }
    }
}