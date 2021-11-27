package com.weskill2.ui.authentication.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.weskill2.R
import com.weskill2.adapter.SignUpProgressAdapter
import com.weskill2.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    lateinit var binding : FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.OtpBtn.setOnClickListener {
            binding.OtpBtn.visibility = View.GONE
            binding.nextBtn.visibility = View.VISIBLE
            binding.otpBox.visibility = View.VISIBLE
            binding.SignUpHeading.text = "Verify"
            binding.smallTitle.text = "Confirm your OTP"
        }

        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_chooseParentFragment)
        }

        binding.signInText.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        return binding.root
    }

}