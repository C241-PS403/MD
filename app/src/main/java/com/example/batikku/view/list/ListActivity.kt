package com.example.batikku.view.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.batikku.R
import com.example.batikku.view.Adapter.ListAdapterBatik
import com.example.batikku.view.Api.ApiConfig
import com.example.batikku.view.Model.ResponseBatikItem
import com.example.batikku.view.main.DetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val batiks = findViewById<RecyclerView>(R.id.rv_batik)


        ApiConfig.getService().getBatik().enqueue(object : Callback<List<ResponseBatikItem>> {
            override fun onResponse(call: Call<List<ResponseBatikItem>>, response: Response<List<ResponseBatikItem>>) {
                if (response.isSuccessful) {
                    val dataBatik = response.body()
                    if (dataBatik != null) {
                        val listAdapterBatik = ListAdapterBatik(dataBatik)
                        batiks.apply {
                            layoutManager = LinearLayoutManager(this@ListActivity)
                            setHasFixedSize(true)
                            adapter = listAdapterBatik
                        }
                        listAdapterBatik.setOnItemClickListener(object : ListAdapterBatik.OnItemClickListener {
                            override fun onItemClick(position: Int) {
                                val selectedBatikItem = dataBatik[position]
                                navigateToDetail(selectedBatikItem)
                            }
                        })
                    }
                } else {
                    // Tangani kesalahan respons
                }
            }

            override fun onFailure(call: Call<List<ResponseBatikItem>>, t: Throwable) {
                // Tangani kegagalan koneksi atau error lain
            }
        })
    }

    private fun navigateToDetail(selectedBatikItem: ResponseBatikItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("BATIK_ITEM", selectedBatikItem)
        startActivity(intent)
    }
}