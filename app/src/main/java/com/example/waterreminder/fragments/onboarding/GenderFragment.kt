package com.example.waterreminder.fragments.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.waterreminder.R
import com.example.waterreminder.databinding.FragmentGenderBinding

class GenderFragment : Fragment() {

    private lateinit var binding: FragmentGenderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenderBinding.inflate(inflater, container, false)
        setupListeners()
        return binding.root
    }

    private fun setupListeners() {
        val images = mapOf(
            binding.FemaleIV to "Female",
            binding.MaleIV to "Male"
        )

        images.keys.forEach { imageView ->
            imageView.setOnClickListener {
                images.forEach { (key, value) ->
                    if (imageView == key) {
                        // Update the background of the selected image view
                        key.setBackgroundResource(R.drawable.genderrectangle_26__1_)

                        // Create a bundle to store the selected gender value
                        val bundle = Bundle().apply { putString("gender", value) }

                        binding.nextTV.setOnClickListener {
                            // Navigate to the next fragment with the selected gender value as an argument
                            findNavController().navigate(R.id.action_genderFragment_to_weightFragment, bundle)
                        }

                        println("gender fragment $bundle")
                    } else {
                        // Update the background of the unselected image view
                        key.setBackgroundResource(0)
                    }
                }
            }
        }
    }
}
