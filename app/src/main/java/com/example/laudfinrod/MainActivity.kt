package com.example.laudfinrod

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIrAudio = findViewById<Button>(R.id.btnIrAudio)
        val btnIrCampanas = findViewById<Button>(R.id.btnIrCampanas)
        val btnIrVideo = findViewById<Button>(R.id.btnIrVideo)
        val btnIrWebView = findViewById<Button>(R.id.btnIrWebView)

        btnIrAudio.setOnClickListener {
            startActivity(Intent(this, AudioActivity::class.java))
        }

        btnIrCampanas.setOnClickListener {
            startActivity(Intent(this, CampanasActivity::class.java))
        }

        btnIrVideo.setOnClickListener {
            startActivity(Intent(this, VideoActivity::class.java))
        }

        btnIrWebView.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        findViewById<Button>(R.id.btnIrEspejo).setOnClickListener {
            startActivity(Intent(this, EspejoActivity::class.java))
        }
    }
}
