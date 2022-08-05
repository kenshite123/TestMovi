package com.example.testmovi.domain.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class City(
    @SerializedName("coord")
    @Expose
    var coord: Coord?,
    @SerializedName("country")
    @Expose
    var country: String?,
    @SerializedName("id")
    @Expose
    var id: Int?,
    @SerializedName("name")
    @Expose
    var name: String?,
    @SerializedName("population")
    @Expose
    var population: Int?,
    @SerializedName("timezone")
    @Expose
    var timezone: Int?
)