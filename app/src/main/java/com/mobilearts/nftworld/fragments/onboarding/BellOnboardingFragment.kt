package com.mobilearts.nftworld.fragments.onboarding

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.R
import com.mobilearts.nftworld.databinding.FragmentBellOnboardingBinding
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.getCustomerInfoWith

class BellOnboardingFragment : Fragment() {
    private lateinit var binding : FragmentBellOnboardingBinding
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        binding = FragmentBellOnboardingBinding.inflate(inflater, container, false)

        sharedPreferences =
            requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        val isFirstOpen = sharedPreferences.getBoolean("isFirstOpen", true)
        println(isFirstOpen)

        if (!isFirstOpen) {
            println(isFirstOpen)
            Purchases.sharedInstance.getCustomerInfoWith({ error -> /* Optional error handling */ }) { purchaserInfo ->
                if (purchaserInfo.entitlements["pro"]?.isActive == true) {
                    // Grant user "pro" access
                    findNavController().navigate(R.id.action_bellOnboardingFragment_to_homeFragment)
                } else {
                    findNavController().navigate(R.id.action_bellOnboardingFragment_to_welcomeFragment)

                }
            }
        }
        println(isFirstOpen)

        binding.StartTV.setOnClickListener {
            findNavController().navigate(BellOnboardingFragmentDirections.actionBellOnboardingFragmentToDrinkOnboardingFragment())
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}
