package com.example.waterreminder.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.waterreminder.R
import com.example.waterreminder.databinding.FragmentInAppBinding
import com.revenuecat.purchases.*


class InAppFragment : Fragment() {

    private lateinit var binding: FragmentInAppBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentInAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.viewMonthly.setOnClickListener {
            selectPlan(binding.viewMonthly, binding.viewAnnually)
            binding.saveTV.visibility = View.INVISIBLE
            binding.viewMonthly.setBackgroundResource(R.drawable.btn_inappselected)
            binding.viewAnnually.setBackgroundResource(R.drawable.btn_inappunselected)

        }

        binding.viewAnnually.setOnClickListener {
            selectPlan(binding.viewAnnually, binding.viewMonthly)
            binding.saveTV.visibility = View.VISIBLE
            binding.viewAnnually.setBackgroundResource(R.drawable.btn_inappselected)
            binding.viewMonthly.setBackgroundResource(R.drawable.btn_inappunselected)
        }

        binding.ContinueTV.setOnClickListener {
            if (binding.viewMonthly.isSelected || binding.viewAnnually.isSelected) {
                findNavController().navigate(InAppFragmentDirections.actionInAppFragmentToHomeFragment())
            } else {
                Toast.makeText(requireContext(), "You have to choose one of the plans!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun selectPlan(selectedView: View, unselectedView: View) {
        selectedView.isSelected = true
        unselectedView.isSelected = false
    }
}