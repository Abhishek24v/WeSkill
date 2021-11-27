package com.weskill2.ui.activities

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.weskill2.R
import com.weskill2.databinding.ActivityCompetitionBinding
import com.weskill2.databinding.DialogEntryDoneBinding
import com.weskill2.ui.fragments.CompetitionEntriesFragment
import com.weskill2.ui.fragments.CompetitionInfoFragment

class CompetitionActivity : AppCompatActivity() {

    private lateinit var b: ActivityCompetitionBinding
    private lateinit var infoTab: TabLayout.Tab
    private lateinit var entriesTab: TabLayout.Tab

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityCompetitionBinding.inflate(layoutInflater)
        setContentView(b.root)
        setTabs()
    }

    private fun setTabs() {
        infoTab = b.competitionTabs.newTab().apply { text = "INFO" }
        entriesTab = b.competitionTabs.newTab().apply { text = "ENTRIES" }
        b.competitionTabs.addTab(infoTab)
        b.competitionTabs.addTab(entriesTab)

        b.competitionTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab) {
                    infoTab -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.competitionFrame, CompetitionInfoFragment()).commit()
                    }
                    entriesTab -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.competitionFrame, CompetitionEntriesFragment()).commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        b.competitionTabs.selectTab(infoTab)
        supportFragmentManager.beginTransaction()
            .replace(R.id.competitionFrame, CompetitionInfoFragment()).commit()

    }
}

class EntryDoneDialog(a: Activity): Dialog(a) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val b = DialogEntryDoneBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.dismiss.setOnClickListener {
            this.dismiss()
        }

    }

}