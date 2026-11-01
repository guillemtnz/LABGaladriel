package com.example.laudfinrod

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class EspejoActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_espejo)

        webView = findViewById(R.id.webViewEspejo)

        // Mantiene la navegación dentro de la app
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        // JavaScript y almacenamiento local para el reproductor
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        // Adaptación responsiva al ancho de la vista
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true

        webView.settings.mediaPlaybackRequiresUserGesture = false

        // Vídeo MP4 directo
        val videoUrl = "https://archive.org/download/BigBuckBunny_124/Content/big_buck_bunny_720p_surround.mp4"

        // Inyección del reproductor HTML5 ocupando el 100% del WebView
        val html = """
            <html>
            <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    html, body { margin: 0; padding: 0; width: 100%; height: 100%; background-color: #000000; }
                    video { position: absolute; top: 0; left: 0; width: 100%; height: 100%; object-fit: contain; }
                </style>
            </head>
            <body>
                <video src="$videoUrl" controls autoplay playsinline></video>
            </body>
            </html>
        """.trimIndent()

        webView.loadDataWithBaseURL("https://archive.org", html, "text/html", "utf-8", null)
    }

    override fun onDestroy() {
        webView.stopLoading()
        webView.destroy()
        super.onDestroy()
    }
}
