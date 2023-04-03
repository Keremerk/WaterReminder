package com.mobilearts.nftworld.objects

import androidx.room.*
import com.mobilearts.nftworld.dataclasses.WaterData

@Dao
interface WaterDataDao {

    @Query("SELECT * FROM water_data")
    suspend fun getAllWaterData(): List<WaterData>

    @Query("SELECT * FROM water_data WHERE id = :id")
    suspend  fun getWaterDataById(id: Int): WaterData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertWaterData(waterData: WaterData)

    @Update
    suspend fun updateWaterData(waterData : WaterData)

    @Delete
    suspend fun deleteWaterData(WaterData: WaterData)

    @Query("UPDATE water_data SET drinkedMl = 0 WHERE id = :id")
    suspend fun resetDrinkedMl(id: Int)

}
