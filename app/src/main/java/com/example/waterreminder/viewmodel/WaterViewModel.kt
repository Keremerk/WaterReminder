package com.example.waterreminder.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.waterreminder.data.WaterData
import com.example.waterreminder.data.WaterRoomDatabase

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
