package com.example.testmovi.domain.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Weather(
    @SerializedName("description")
    @Expose
    var description: String?,
    @SerializedName("icon")
    @Expose
    var icon: String?,
    @SerializedName("id")
    @Expose
    var id: Int?,
    @SerializedName("main")
    @Expose
    var main: String?
)