package com.example.pianosense

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pianosense.MainActivity
import com.example.pianosense.R


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logoImageView = findViewById<ImageView>(R.id.logoImageView)


        // Glide kullanarak GIF gösterimi
        Glide.with(this).asGif().load(R.drawable.sense).into(logoImageView)



        // 3 saniye bekleme ve MainActivity'ye geçiş
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 6000) // 3000 milisaniye = 3 saniye

    }
}
