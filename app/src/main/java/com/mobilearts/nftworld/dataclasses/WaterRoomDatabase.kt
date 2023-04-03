package com.mobilearts.nftworld.dataclasses

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobilearts.nftworld.objects.WaterDataDao


@Database(entities = [WaterData::class], version = 1)
abstract class WaterRoomDatabase : RoomDatabase() {
    abstract fun WaterDataDao() : WaterDataDao
    companion object {
        @Volatile
        private var INSTANCE : WaterRoomDatabase? = null

         fun getDatabase(context : Context) : WaterRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WaterRoomDatabase::class.java,
                    "water_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}