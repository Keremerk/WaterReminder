package com.mobilearts.nftworld.fragments.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.databinding.FragmentDrinkOnboardingBinding

class DrinkOnboardingFragment : Fragment() {
    private  lateinit var binding: FragmentDrinkOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDrinkOnboardingBinding.inflate(inflater, container, false)

        binding.startTV2.setOnClickListener {
            findNavController().navigate(DrinkOnboardingFragmentDirections.actionDrinkOnboardingFragmentToRocketOnboardingFragment())
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}