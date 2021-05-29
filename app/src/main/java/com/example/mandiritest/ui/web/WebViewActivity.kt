package com.example.mandiritest.ui.web

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mandiritest.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val link = intent.getStringExtra(WEBVIEW_PARAM_LINK)
        if (link == null) {
            finish()
            return
        }
        try {
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.settings.domStorageEnabled = true
            binding.webView.settings.builtInZoomControls = true
            binding.webView.settings.displayZoomControls = false
            binding.webView.loadUrl(link)
        } catch (e: Exception) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            try {
                startActivity(browserIntent)
            } catch (e: Exception) {
                Log.e("Error", "no activity found to open url")
            }
            e.printStackTrace()
        }
    }

    companion object {
        var WEBVIEW_PARAM_LINK = "link"
        fun createIntent(origin: Context?, link: String?): Intent {
            val intent = Intent(origin, WebViewActivity::class.java)
            intent.putExtra(WEBVIEW_PARAM_LINK, link)
            return intent
        }
    }
}