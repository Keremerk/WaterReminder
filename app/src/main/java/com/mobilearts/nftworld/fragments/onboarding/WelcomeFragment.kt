package com.mobilearts.nftworld.fragments.onboarding

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private  lateinit var binding: FragmentWelcomeBinding
    private val navDelayMs = 2000L // 2 seconds
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInAppFragment())
        }, navDelayMs)

        // Inflate the layout for this fragment
        return binding.root
    }


}