package com.mobilearts.nftworld.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.mobilearts.nftworld.databinding.FragmentHomeBinding
import com.mobilearts.nftworld.dataclasses.WaterRoomDatabase
import com.mobilearts.nftworld.objects.BottomObjects
import com.mobilearts.nftworld.objects.Temp
import com.mobilearts.nftworld.viewmodel.WaterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel : WaterViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[WaterViewModel::class.java]
        scheduleDailyTask()

        binding.drinkImage.setImageResource(
            BottomObjects.getBottomAdapterList(Temp.topAdapterPosition)[Temp.bottomAdapterPosition].imageResIdBottom
        )

        viewModel.neededML.observe(viewLifecycleOwner) { neededML ->
            binding.neededMLTV.text = "$neededML ml"
        }
        viewModel.drankML.observe(viewLifecycleOwner) { drankML ->
            binding.drinkedTV.text = "$drankML ml"
        }

        val waterDataDao = context?.let { WaterRoomDatabase.getDatabase(it).WaterDataDao() }

        viewModel.viewModelScope.launch {
            val waterData = waterDataDao?.getWaterDataById(0)
            if (waterData != null) {
                binding.drinkedTV.text = "${waterData.drinkedMl} mL"
            }

            val percentage =
                BottomObjects.getBottomAdapterList(Temp.topAdapterPosition)[Temp.bottomAdapterPosition].percentage

            binding.apply {
                chooseDrinks.setOnClickListener {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMenuFragment())
                }
                settingsButton.setOnClickListener {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())
                }
                plusIV.setOnClickListener {
                    viewModel.viewModelScope.launch {
                        val drinkedMl = ETdrinkedML.text.toString().toIntOrNull()
                        val drinkedPercentage = (drinkedMl?.times(percentage))?.div(100)
                        if (waterData != null && drinkedMl != null) {
                            viewModel.updateDrankML(
                                drinkedPercentage!!,
                                waterData,
                                requireContext()
                            )
                        }
                    }
                }
                minusIV.setOnClickListener {
                    viewModel.viewModelScope.launch {
                        val drinkedMl = ETdrinkedML.text.toString().toIntOrNull()
                        val drinkedPercentage = (drinkedMl?.times(percentage))?.div(100)
                        if (waterData != null && drinkedMl != null) {
                            viewModel.updateDrankML(
                                -drinkedPercentage!!,
                                waterData,
                                requireContext()
                            )
                        }
                    }
                }
            }
        }

        return binding.root
    }

    private fun scheduleDailyTask() {
        val settings = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val lastTimeStarted = settings.getInt("last_time_started", -1)
        val calendar = Calendar.getInstance()
        val today = calendar[Calendar.DAY_OF_YEAR]
        if (today != lastTimeStarted) {
            //startSomethingOnce();
            val waterDataDao = context?.let { WaterRoomDatabase.getDatabase(it).WaterDataDao() }
            CoroutineScope(Dispatchers.IO).launch {
                waterDataDao?.resetDrinkedMl(0)
                println("DrinkedML is set to 0")
            }
            val editor = settings.edit()
            editor.putInt("last_time_started", today)
            editor.apply()
        }
    }
}
