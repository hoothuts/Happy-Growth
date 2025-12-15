package com.example.projectuas.parenting

import android.os.Bundle
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

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(url!!)
    }
}