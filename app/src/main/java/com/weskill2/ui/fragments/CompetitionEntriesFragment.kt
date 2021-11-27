package com.weskill2.ui.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.weskill2.R
import com.weskill2.databinding.FragmentCompetitionEntriesBinding
import com.weskill2.ui.activities.EntryDoneDialog
import com.weskill2.ui.components.adapters.EntriesAdapter

class CompetitionEntriesFragment : Fragment() {

    private lateinit var b: FragmentCompetitionEntriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentCompetitionEntriesBinding.inflate(layoutInflater)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEntries()
        setClicks()

    }

    private fun setClicks() {
        b.enrollNow.setOnClickListener {
            EntryDoneDialog(activity as Activity).show()
        }
    }

    private fun setEntries() {
        b.entriesRecycler.layoutManager = GridLayoutManager(context,3)
        b.entriesRecycler.adapter = EntriesAdapter()
    }
}