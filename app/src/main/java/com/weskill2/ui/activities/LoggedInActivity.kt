package com.weskill2.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weskill2.R
import com.weskill2.databinding.ActivityLoggedInBinding
import com.weskill2.ui.fragments.*

class LoggedInActivity : AppCompatActivity() {

    private lateinit var b: ActivityLoggedInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityLoggedInBinding.inflate(layoutInflater)
        setContentView(b.root)
        setBottomBar()
    }

    private fun setBottomBar() {
        b.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.logged_in_frame, HomeFragment()).commit()
                }
                R.id.discover -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.logged_in_frame, DiscoverFragment()).commit()
                }
                R.id.train -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.logged_in_frame, TrainFragment()).commit()
                }
                R.id.community -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.logged_in_frame, CommunityFragment()).commit()
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.logged_in_frame, LeaderBoardFragment()).commit()
                }
            }
            return@setOnItemSelectedListener true
        }
        b.bottomNav.selectedItemId = R.id.community
    }
}