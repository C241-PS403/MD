package com.example.batikku.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.batikku.R
import com.example.batikku.databinding.ActivityLoginBinding
import com.example.batikku.databinding.ActivityMainBinding
import com.example.batikku.view.list.ListActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ListbatikBtn.setOnClickListener{
            val intent = Intent(this , ListActivity::class.java)
            startActivity(intent)
        }
    }
}