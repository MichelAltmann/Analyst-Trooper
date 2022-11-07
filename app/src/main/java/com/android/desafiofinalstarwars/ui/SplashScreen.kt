package com.android.desafiofinalstarwars.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.android.desafiofinalstarwars.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val imagem = findViewById<ImageView>(R.id.splash_image)
        imagem.alpha = 0f
        imagem.animate().setDuration(2000).alpha(1f).scaleXBy(3f).scaleYBy(3f).withEndAction{
                val it = Intent(this, MainActivity::class.java)
                startActivity(it)
                overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out)
                finish()
        }
    }
}