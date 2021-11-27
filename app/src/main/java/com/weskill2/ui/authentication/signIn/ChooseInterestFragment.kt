package com.weskill2.ui.authentication.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.weskill2.R
import com.weskill2.databinding.FragmentChooseInterestBinding

class ChooseInterestFragment : Fragment() {

    private lateinit var binding : FragmentChooseInterestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChooseInterestBinding.inflate(inflater, container, false)

        binding.NextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_chooseInterestFragment_to_communityFragment)
        }
        return binding.root
    }

}