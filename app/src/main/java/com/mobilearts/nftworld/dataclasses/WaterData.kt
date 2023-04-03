package com.mobilearts.nftworld.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Water_data")

data class WaterData(
    @PrimaryKey(autoGenerate = false)
    val id : Int = 0,
    val gender : String,
    val weight : Int,
    val exerciseType : String?,
    val neededML : Int,
    var drinkedMl : Int = 0
)