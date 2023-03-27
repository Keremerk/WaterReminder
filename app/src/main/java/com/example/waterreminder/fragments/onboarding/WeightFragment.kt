package com.example.waterreminder.fragments.onboarding

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.waterreminder.R
import com.example.waterreminder.databinding.FragmentWeightBinding

class WeightFragment : Fragment() {
    private lateinit var binding : FragmentWeightBinding
    private var weight = 65
    private var gender = ""

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        binding = FragmentWeightBinding.inflate(inflater, container, false)
        gender = arguments?.getString("gender") ?: ""

        binding.apply {
            setWeightTV.setOnClickListener { showNumberPickerDialog() }
            nextButtonTV.setOnClickListener {
                findNavController().navigate(
                    WeightFragmentDirections.actionWeightFragmentToExerciseFragment(
                        weight,
                        gender
                    )
                )
            }
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun showNumberPickerDialog() {
        val view = layoutInflater.inflate(R.layout.number_picker_dialog, null)
        val numberPicker = view.findViewById<NumberPicker>(R.id.number_picker)
        numberPicker.minValue = 1
        numberPicker.maxValue = 150

        val alertDialogBuilder = AlertDialog.Builder(requireContext()).setView(view).apply {
            setPositiveButton("OK") { dialog, _ ->
                weight = numberPicker.value
                binding.displayWeightTV.text = "$weight kg"
                dialog.dismiss()
            }
            setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
        }

        // Disable the input focus of the EditText view
        numberPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
