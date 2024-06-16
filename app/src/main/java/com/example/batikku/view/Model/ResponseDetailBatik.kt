package com.example.batikku.view.Model

import com.google.gson.annotations.SerializedName

data class ResponseBatikDetail(
    @SerializedName("image") val image: String?,
    @SerializedName("batik_name") val batikName: String?,
    @SerializedName("batik_origin") val batikOrigin: String?,
    @SerializedName("batik_meaning") val batikMeaning: String?,
    @SerializedName("batik_history") val batikHistory: String?
)
