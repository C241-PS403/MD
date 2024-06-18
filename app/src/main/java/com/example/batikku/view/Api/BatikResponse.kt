package com.example.batikku.view.Api

import com.google.gson.annotations.SerializedName
data class BatikResponse(
    @SerializedName("batikName")
    val name: String,
    val confidence: Float
)