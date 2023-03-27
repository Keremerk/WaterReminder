package com.example.waterreminder.alarms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.waterreminder.data.WaterRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DailyTaskReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            val waterDataDao = WaterRoomDatabase.getDatabase(context).WaterDataDao()
            CoroutineScope(Dispatchers.IO).launch {
                waterDataDao.resetDrinkedMl(0)
                println("DrinkedML is set to 0")
            }
        }
    }
}
