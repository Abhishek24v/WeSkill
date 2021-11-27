package com.weskill2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import com.weskill2.R
import com.weskill2.adapter.VPObAdapter
import com.weskill2.databinding.FragmentOnBoardingBinding

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)

        val slider = binding.obSlider
        //val tabDot = view.findViewById<TabLayout>(R.id.tl_onBoarding)
        val tabDot = binding.obDots

        val list = ArrayList<Fragment>()
        list.add(
            ListOnBoardingFragment(
                "Wide ranging courses from Arts, Dance, Music, Workshops",
                R.drawable.onboarding_s1,
                "Wide Selection of Activities",
                R.drawable.whistle
            )
        )
        list.add(
            ListOnBoardingFragment(
                "With best in class instructors",
                R.drawable.onboarding_s2,
                "Live Online Classes ",
                R.drawable.rocket
            )
        )
        list.add(
            ListOnBoardingFragment(
                "With AI assissted feeback and support, you can learn at your own pace to mastery",
                R.drawable.onboarding_s3,
                "Personalized learning",
                R.drawable.robot_comp
            )
        )

        val adapter = VPObAdapter(this, list)
        slider.adapter = adapter
        tabDot.setViewPager2(slider)

        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }

        binding.signInBtn.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_signUpFragment)
        }
       /* TabLayoutMediator(tabDot,slider){tab,position ->
        }.attach()
*/
        /* slider.orientation = ViewPager2.ORIENTATION_HORIZONTAL
         var pos = 0
         slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
             override fun onPageSelected(position: Int) {
                 super.onPageSelected(position)
                 pos = position
                 *//*if (position == list.size - 1) {
                    this@OnBoardingActivity.findViewById<Button>(R.id.next_button).apply {
                        text = "Add Learner Profile"
                    }
                    this@OnBoardingActivity.findViewById<Button>(R.id.skip_button).apply {
                        visibility = View.GONE
                    }
                } else {
                    this@OnBoardingActivity.findViewById<Button>(R.id.next_button).apply {
                        text = "Next"
                    }
                    this@OnBoardingActivity.findViewById<Button>(R.id.skip_button).apply {
                        visibility = View.VISIBLE
                    }
                }*//*
            }
        })

*/
        return binding.root
    }

}