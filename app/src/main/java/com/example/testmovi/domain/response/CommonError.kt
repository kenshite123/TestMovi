package com.example.testmovi.domain.response

import com.google.gson.annotations.SerializedName

data class CommonError(
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("message")
    val message: String?
)