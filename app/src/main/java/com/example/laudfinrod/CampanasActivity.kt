package com.example.laudfinrod

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CampanasActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campanas)

        val btnTocar = findViewById<Button>(R.id.btnTocarAlarma)
        val btnDetener = findViewById<Button>(R.id.btnDetenerAlarma)
        val btnReiniciar = findViewById<Button>(R.id.btnReiniciarAlarma)

        // Idle -> Initialized -> Prepared
        mediaPlayer = MediaPlayer.create(this, R.raw.campanas_gondor)?.apply {
            isLooping = true // Bucle infinito
        }

        btnTocar.setOnClickListener {
            if (mediaPlayer?.isPlaying == false) {
                mediaPlayer?.start() // Started
            }
        }

        btnDetener.setOnClickListener {
            mediaPlayer?.let { mp ->
                if (mp.isPlaying) {
                    mp.stop() // Stopped: no se puede hacer start() sin re-preparar
                    mp.prepare() // Re-preparación -> Prepared, posición en 0
                }
            }
        }

        btnReiniciar.setOnClickListener {
            // Vuelve al segundo cero sin pausar ni detener la reproducción
            mediaPlayer?.seekTo(0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release() // End
        mediaPlayer = null
    }
}
