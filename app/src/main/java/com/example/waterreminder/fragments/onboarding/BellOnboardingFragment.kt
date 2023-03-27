package com.example.waterreminder.fragments.onboarding

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.waterreminder.R
import com.example.waterreminder.databinding.FragmentBellOnboardingBinding

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
            findNavController().navigate(R.id.action_bellOnboardingFragment_to_welcomeFragment)

            /*
            Purchases.sharedInstance.getCustomerInfoWith({   /* Optional error handling */ }) { purchaserInfo ->
                if (purchaserInfo.entitlements["pro"]?.isActive == true) {
                    // Grant user "pro" access
                    findNavController().navigate(R.id.action_bellOnboardingFragment_to_homeFragment)
                } else {
                    findNavController().navigate(R.id.action_bellOnboardingFragment_to_welcomeFragment)

                }


            }
             */

        }
        println(isFirstOpen)

        binding.StartTV.setOnClickListener {
            findNavController().navigate(BellOnboardingFragmentDirections.actionBellOnboardingFragmentToDrinkOnboardingFragment())
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}
