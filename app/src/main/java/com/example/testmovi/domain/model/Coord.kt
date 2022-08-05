package com.example.testmovi.domain.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Coord(
    @SerializedName("lat")
    @Expose
    var lat: Double?,
    @SerializedName("lon")
    @Expose
    var lon: Double?
)