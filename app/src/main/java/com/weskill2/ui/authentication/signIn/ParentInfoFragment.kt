package com.weskill2.ui.authentication.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.weskill2.R
import com.weskill2.databinding.FragmentParentInfoBinding

class ParentInfoFragment : Fragment() {

    private lateinit var binding: FragmentParentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
      binding = FragmentParentInfoBinding.inflate(inflater, container, false)

        binding.NextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_parentInfoFragment_to_parentsExtraFragment)
        }

        return binding.root
    }

}