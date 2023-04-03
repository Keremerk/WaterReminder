package com.mobilearts.nftworld.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobilearts.nftworld.dataclasses.WaterData
import com.mobilearts.nftworld.dataclasses.WaterRoomDatabase

class WaterViewModel : ViewModel() {
    // MutableLiveData objects to hold the needed and drank water amounts
    val neededML = MutableLiveData<Int>()
    val drankML = MutableLiveData<Int>()

    // Function to update the drank water amount
    suspend fun updateDrankML(amount : Int, waterData : WaterData, context : Context) {
        val currentAmount = waterData.drinkedMl + amount
        drankML.value = currentAmount
        waterData.drinkedMl = currentAmount
        WaterRoomDatabase.getDatabase(context).WaterDataDao().updateWaterData(waterData)
    }
}
