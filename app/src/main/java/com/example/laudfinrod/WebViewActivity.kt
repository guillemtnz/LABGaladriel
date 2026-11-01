package com.example.laudfinrod

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        webView = findViewById(R.id.webViewPalantir)

        // Evita que el vídeo salte al navegador externo
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        // JavaScript para el reproductor de YouTube
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true

        //  ID del vídeo de YouTube
        val videoId = "R4qfx3ZMSB8"

        // Iframe oficial de YouTube adaptado al 100% de la vista
        val iframeHtml = """
            <html>
            <head>
                <style>
                    body { margin: 0; padding: 0; background-color: #000000; }
                    .video-container { position: relative; width: 100%; height: 100%; }
                    iframe { position: absolute; top: 0; left: 0; width: 100%; height: 100%; }
                </style>
            </head>
            <body>
                <div class="video-container">
                    <iframe src="https://www.youtube.com/embed/$videoId" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
                </div>
            </body>
            </html>
        """.trimIndent()

        // Carga en el WebView
        webView.loadDataWithBaseURL("https://www.youtube.com", iframeHtml, "text/html", "utf-8", null)
    }

    // Libero memoria al salir
    override fun onDestroy() {
        webView.stopLoading()
        webView.destroy()
        super.onDestroy()
    }
}
