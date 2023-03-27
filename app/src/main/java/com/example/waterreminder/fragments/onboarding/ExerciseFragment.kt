package com.example.waterreminder.fragments.onboarding

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.waterreminder.R
import com.example.waterreminder.databinding.FragmentExerciseBinding
import com.example.waterreminder.data.WaterData
import com.example.waterreminder.data.WaterRoomDatabase
import kotlinx.coroutines.*

class ExerciseFragment : Fragment() {
    private lateinit var binding : FragmentExerciseBinding
    private val args by navArgs<ExerciseFragmentArgs>()
    private lateinit var sharedPreferences: SharedPreferences

    private val scope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?
    ) : View {
        binding = FragmentExerciseBinding.inflate(inflater, container, false)
        setupListeners()
        sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)



        println("gender at exercise top" + args.ARGGENDER2)
        println("weight at exercise top " + args.ARGWEIGHT)

        binding.nextTV2.setOnClickListener {
           sharedPreferences.edit().putBoolean("isFirstOpen", false).apply()
            // Get the selected exercise type
            val selectedExerciseType = when {
                binding.NoExerciseconstraintLayout.isSelected -> "no exercise"
                binding.StableconstraintLayout.isSelected -> "stable"
                binding.ActiveconstraintLayout.isSelected -> "active"
                else -> null
            }

            val neededml = calculateWaterIntake(args.ARGGENDER2!!, args.ARGWEIGHT, selectedExerciseType)
            if (selectedExerciseType != null) {
                // Insert the water data into the database
                val waterData = WaterData(
                    gender = args.ARGGENDER2!!,
                    weight = args.ARGWEIGHT,
                    exerciseType = selectedExerciseType,
                    neededML =neededml.toInt(),
                    drinkedMl = 0
                )
                scope.launch {
                    val db = withContext(Dispatchers.IO) {
                        WaterRoomDatabase.getDatabase(requireContext())
                    }
                    db.WaterDataDao().insertWaterData(waterData)
                    println(waterData)
                }

                findNavController().navigate(ExerciseFragmentDirections.actionExerciseFragmentToWelcomeFragment())
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setupListeners() {
        binding.apply {
            // Set click listeners for the category options
            NoExerciseconstraintLayout.setOnClickListener { selectExerciseType(it) }
            StableconstraintLayout.setOnClickListener { selectExerciseType(it) }
            ActiveconstraintLayout.setOnClickListener { selectExerciseType(it) }
        }
    }


    private fun selectExerciseType(view : View) {
        // Reset all toggle ImageViews
        binding.noExerciseToggle.setImageResource(R.drawable.basicellipse_6__1_)
        binding.stableToggle.setImageResource(R.drawable.basicellipse_6__1_)
        binding.ActiveToggle.setImageResource(R.drawable.basicellipse_6__1_)
        binding.NoExerciseconstraintLayout.setBackgroundResource(R.drawable.basicrectangle_29__1_)
        binding.StableconstraintLayout.setBackgroundResource(R.drawable.basicrectangle_29__1_)
        binding.ActiveconstraintLayout.setBackgroundResource(R.drawable.basicrectangle_29__1_)

        // Select the clicked category
        view.isSelected = true
        if (view.isSelected) {
            view.setBackgroundResource(R.drawable.chosenrectangle_28__1_)
            when (view) {
                binding.NoExerciseconstraintLayout -> {
                    binding.noExerciseToggle.setImageResource(R.drawable.img_onboardingselected)
                }
                binding.StableconstraintLayout -> {
                    binding.stableToggle.setImageResource(R.drawable.img_onboardingselected)
                }
                binding.ActiveconstraintLayout -> {
                    binding.ActiveToggle.setImageResource(R.drawable.img_onboardingselected)
                }
            }
        } else {
            view.setBackgroundResource(R.drawable.basicrectangle_29__1_)
        }
    }

    private fun calculateWaterIntake(gender: String, weight: Int, selectedExerciseType: String?): Double {
        val baseWaterIntake = if (gender == "female") 2.7 else 3.7
        var waterIntake = baseWaterIntake * weight / 60.0 // assuming weight is in kg
        if (weight > 90) {
            waterIntake += 1.1 * baseWaterIntake
        }
        if (selectedExerciseType != null) {
            when (selectedExerciseType) {
                "stable" -> waterIntake += 1.1 * baseWaterIntake
                "active" -> waterIntake += 1.2 * baseWaterIntake
            }
        }
        return waterIntake * 1000
    }

}