package com.example.batikku.view.Model

import com.google.gson.annotations.SerializedName

data class PredictResponse(
    @SerializedName("name") val name: String
)