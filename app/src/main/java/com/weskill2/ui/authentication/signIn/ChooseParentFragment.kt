package com.weskill2.ui.authentication.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.weskill2.R
import com.weskill2.databinding.FragmentChooseParentBinding

class ChooseParentFragment : Fragment() {

    private lateinit var binding : FragmentChooseParentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChooseParentBinding.inflate(inflater, container, false)

        binding.NextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_chooseParentFragment_to_parentInfoFragment)
        }
        binding.femaleBtn.setOnClickListener {  }
        binding.maleBtn.setOnClickListener {  }

        return binding.root
    }

}