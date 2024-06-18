package com.example.batikku.view.Api

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface BatikApiService {
    @Multipart
    @POST("predict")
    fun predictBatik(@Part image: MultipartBody.Part): Call<BatikResponse>
}

object ApiConfig {

    const val BASE_URL = "https://batik-backend-pcy5nqycuq-et.a.run.app/"

    fun getService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}