package com.mobilearts.nftworld.fragments.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.databinding.FragmentRocketOnboardingBinding

class RocketOnboardingFragment : Fragment() {
    private  lateinit var binding: FragmentRocketOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketOnboardingBinding.inflate(inflater, container, false)

        binding.startTV3.setOnClickListener {
            findNavController().navigate(RocketOnboardingFragmentDirections.actionRocketOnboardingFragmentToGenderFragment()) }
        // Inflate the layout for this fragment
        return binding.root
    }
}