package com.example.waterreminder.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waterreminder.adapter.BottomAdapterRV
import com.example.waterreminder.adapter.TopAdapterRV
import com.example.waterreminder.databinding.FragmentMenuBinding
import com.example.waterreminder.utils.BottomObjects.getBottomAdapterList
import com.example.waterreminder.utils.Temp
import com.example.waterreminder.utils.TopObjects.topAdapterList

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var topAdapterRV: TopAdapterRV
    private lateinit var bottomAdapterRV: BottomAdapterRV

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        binding.backButtonMenu.setOnClickListener {
            findNavController().navigateUp()
        }

        setAdapters()

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapters() {
        binding.RecyclerViewTop.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topAdapterRV = TopAdapterRV(topAdapterList) { position ->
            // Update the data of the bottom adapter based on the clicked position
            bottomAdapterRV.dataSet = getBottomAdapterList(position)
            bottomAdapterRV.notifyDataSetChanged()

            // Update the background of the clicked item
            topAdapterRV.setSelectedPosition(position)

            // Reset the background of the previously selected item
            if (topAdapterRV.selectedPositionTop >= 0) {
                topAdapterRV.setSelectedPosition(topAdapterRV.selectedPositionTop)
            }

            // Save the selected position
            Temp.topAdapterPosition = position
        }
        binding.RecyclerViewTop.adapter = topAdapterRV

        binding.RecyclerViewBottom.layoutManager = GridLayoutManager(requireContext(), 2)
        bottomAdapterRV = BottomAdapterRV(getBottomAdapterList(0)) { position ->
            Temp.bottomAdapterPosition = position
            findNavController().navigateUp()
        }
        binding.RecyclerViewBottom.adapter = bottomAdapterRV
    }
}
