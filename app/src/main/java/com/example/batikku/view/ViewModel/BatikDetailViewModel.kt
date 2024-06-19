package com.example.batikku.view.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.batikku.view.Api.ApiConfig
import com.example.batikku.view.Model.ResponseBatikDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BatikDetailViewModel : ViewModel() {

    private val apiService = ApiConfig.getService()

    private val _batikDetail = MutableLiveData<ResponseBatikDetail>()
    val batikDetail: LiveData<ResponseBatikDetail>
        get() = _batikDetail

    fun getBatikDetail(batikId: Int) {
        apiService.getBatikDetail(batikId).enqueue(object : Callback<ResponseBatikDetail> {
            override fun onResponse(call: Call<ResponseBatikDetail>, response: Response<ResponseBatikDetail>) {
                if (response.isSuccessful) {
                    _batikDetail.value = response.body()
                } else {
                    // Handle error response
                    Log.e("BatikDetailViewModel", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ResponseBatikDetail>, t: Throwable) {
                // Handle network failure
                Log.e("BatikDetailViewModel", "Failure: ${t.message}", t)
            }
        })
    }
}