package com.example.testmovi.domain.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class FeelsLike(
    @SerializedName("day")
    @Expose
    var day: Double?,
    @SerializedName("eve")
    @Expose
    var eve: Double?,
    @SerializedName("morn")
    @Expose
    var morn: Double?,
    @SerializedName("night")
    @Expose
    var night: Double?
)