package com.example.batikku.view.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PredictApiConfig {
    private const val PREDICT_BASE_URL = "https://backend-modelpredict-pcy5nqycuq-et.a.run.app/"

    fun getPredictService(): PredictApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(PREDICT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PredictApiService::class.java)
    }
}