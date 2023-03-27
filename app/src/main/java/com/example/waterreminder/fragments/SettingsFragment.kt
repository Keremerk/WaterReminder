package com.example.waterreminder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.waterreminder.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
 private lateinit var binding : FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.apply {
            getPremiumTV.setOnClickListener {
                findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToInAppFragment())
            }
            notificationSettingsTV.setOnClickListener {
                findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToReminderFragment())
            }
            backButtonIVSettings.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}