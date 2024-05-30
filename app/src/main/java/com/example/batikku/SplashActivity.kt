package com.example.batikku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.batikku.view.login.LoginActivity
import com.example.batikku.view.main.MainActivity
import com.example.batikku.view.register.RegisterActivity

class SplashActivity : AppCompatActivity() {
    private val Splash_time_out : Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }, Splash_time_out)
    }
    }
