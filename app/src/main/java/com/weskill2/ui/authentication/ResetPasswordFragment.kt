package com.weskill2.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.weskill2.R
import com.weskill2.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {

    private lateinit var binding : FragmentResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)

        binding.otpBtn.setOnClickListener {
            binding.otpBox.visibility = View.VISIBLE
            binding.setOtpBtn.visibility = View.VISIBLE
            binding.otpBtn.visibility = View.GONE
            binding.resendText.visibility = View.VISIBLE
        }

        binding.setPassBtn.setOnClickListener {
            binding.getOtpLayout.visibility = View.GONE
            binding.setPasswordLayout.visibility = View.VISIBLE

            findNavController().navigate(R.id.action_resetPasswordFragment_to_communityFragment)
        }

        return binding.root
    }

}