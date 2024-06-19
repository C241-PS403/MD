package com.example.batikku.view.Api

import com.example.batikku.view.Model.PredictResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PredictApiService {
    // Endpoint untuk memprediksi nama batik dari gambar
    @Multipart
    @POST("/predict")
    fun predictBatikName(
        @Part file: MultipartBody.Part
    ): Call<PredictResponse>  // Ganti ResponseBody dengan tipe data yang sesuai dengan respons API
}