package com.weskill2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weskill2.databinding.ActivityViewEntryBinding

class ViewEntryActivity : AppCompatActivity() {

    private lateinit var b: ActivityViewEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityViewEntryBinding.inflate(layoutInflater)
        setContentView(b.root)

        setPlayer()

    }

    private fun setPlayer() {

    }
}