package com.hfad.myweatherapp.model.dto


import com.google.gson.annotations.SerializedName

data class Parts(
    @SerializedName("day")
    val day: Day,
    @SerializedName("day_short")
    val dayShort: DayShort,
    @SerializedName("morning")
    val morning: Morning,
    @SerializedName("night")
    val night: Night,
    @SerializedName("night_short")
    val nightShort: NightShort
)