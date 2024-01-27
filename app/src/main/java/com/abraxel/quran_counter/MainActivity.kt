package com.abraxel.quran_counter

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus

class MainActivity : AppCompatActivity() {
    private lateinit var myWebView: WebView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val webClient = MyWebClient()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(applicationContext) { _: InitializationStatus? -> }
        val mAdView = findViewById<AdView>(R.id.adView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        myWebView = findViewById(R.id.webview)
        myWebView.webViewClient = webClient
        myWebView.setInitialScale(1)
        myWebView.isClickable = true
        val webSettings = myWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.domStorageEnabled = true
        webSettings.allowContentAccess = true
        webSettings.allowFileAccess = false
        webSettings.setSupportZoom(true)
        myWebView.loadUrl(Constants.BASE_URL)

        swipeRefreshLayout.setOnRefreshListener {
            myWebView.reload()
        }

        myWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    class MyWebClient : WebViewClient() {
        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
            handler.cancel()
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            (view.parent as SwipeRefreshLayout).isRefreshing = true
            view.loadUrl(request.url.toString())
            return true
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack()
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onResume() {
        swipeRefreshLayout.isRefreshing = false
        super.onResume()
    }
}