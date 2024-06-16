package com.example.batikku.view.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.batikku.R
import com.example.batikku.view.Model.ResponseBatikItem
import com.example.batikku.view.ViewModel.BatikDetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var batikDetailViewModel: BatikDetailViewModel // Pastikan Anda telah membuat ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageView: ImageView = findViewById(R.id.imageView7)
        val nameTextView: TextView = findViewById(R.id.textView_nama_batik)
        val originTextView: TextView = findViewById(R.id.textView_daerah_detail)
        val meaningTextView: TextView = findViewById(R.id.textView_makna_detail)
        val historyTextView: TextView = findViewById(R.id.textView_sejarah_detail)

        // Ambil data dari Intent
        val batikItem = intent.getParcelableExtra<ResponseBatikItem>("BATIK_ITEM")

        // Pastikan batikItem tidak null sebelum mengambil batikId
        val batikId = batikItem?.batikId ?: -1 // Default value jika batikId null atau tidak ada

        // Setup ViewModel dan observer untuk mendapatkan data detail batik
        batikDetailViewModel = ViewModelProvider(this).get(BatikDetailViewModel::class.java)
        batikDetailViewModel.getBatikDetail(batikId)
        batikDetailViewModel.batikDetail.observe(this, Observer { batikDetail ->
            // Update UI dengan data detail batik (origin dan meaning)
            if (batikDetail != null) {
                originTextView.text = batikDetail.batikOrigin
                meaningTextView.text = batikDetail.batikMeaning
                // Load image using Glide or any other method
                Glide.with(this)
                    .load(batikDetail.image)
                    .into(imageView)
                nameTextView.text = batikDetail.batikName
                historyTextView.text = batikDetail.batikHistory
            }
        })
    }
}