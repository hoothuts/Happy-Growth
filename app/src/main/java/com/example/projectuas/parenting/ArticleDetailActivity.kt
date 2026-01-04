package com.example.projectuas.parenting

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuas.databinding.ActivityArticleDetailBinding

class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val url = intent.getStringExtra("url")

        binding.tvTitle.text = title

        binding.webView.apply {
            webViewClient = WebViewClient() // penting agar tidak keluar app
            settings.javaScriptEnabled = true
            loadUrl(url ?: "")
        }
    }
}
