package com.example.projectuas.parenting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuas.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val youtubeUrl = intent.getStringExtra("youtubeUrl")

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(youtubeUrl!!)
    }
}