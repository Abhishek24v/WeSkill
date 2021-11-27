package com.weskill2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.weskill2.R

class ListOnBoardingFragment : Fragment {

    var text: String = ""
    var drawable: Int = 0
    var text2: String = ""
    var icon : Int = 0

    constructor(small: String, background: Int, head: String = "",icon: Int) : super() {
        this.text = small
        this.drawable = background
        this.text2 = head
        this.icon = icon
    }
    constructor() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_list_on_boarding, container, false)

        view.findViewById<TextView>(R.id.small_title).text = text
        view.findViewById<LinearLayout>(R.id.ob_image).setBackgroundResource(drawable)
        view.findViewById<TextView>(R.id.head_title).text = text2
        view.findViewById<ImageView>(R.id.icon).setImageResource(icon)

        return view
    }

}