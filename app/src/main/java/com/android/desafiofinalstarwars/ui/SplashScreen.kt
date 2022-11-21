package com.android.desafiofinalstarwars.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.android.desafiofinalstarwars.R

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val image = findViewById<ImageView>(R.id.splash_image)
        image.alpha = 0f
        image.animate().setDuration(2000).alpha(1f).scaleXBy(1.5f).scaleYBy(1.5f).withEndAction{
                val it = Intent(this, MainActivity::class.java)
                startActivity(it)
                overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out)
                finish()
        }
    }
}