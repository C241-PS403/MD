package com.example.batikku.view.Api

import com.example.batikku.view.Model.ResponseBatikDetail
import com.example.batikku.view.Model.ResponseBatikItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api")
    fun getBatik(): Call<List<ResponseBatikItem>>

    @GET("/api/{batikId}")
    fun getBatikDetail(@Path("batikId") batikId: Int): Call<ResponseBatikDetail>
}