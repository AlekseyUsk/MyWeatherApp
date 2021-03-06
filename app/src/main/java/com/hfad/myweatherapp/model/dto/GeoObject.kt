package com.hfad.myweatherapp.model.dto


import com.google.gson.annotations.SerializedName

data class GeoObject(
    @SerializedName("country")
    val country: Any,
    @SerializedName("district")
    val district: Any,
    @SerializedName("locality")
    val locality: Any,
    @SerializedName("province")
    val province: Any
)