package com.example.projectuas.model

data class ParentingItem(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val type: String = "",      // "article" | "video"
    val articleUrl: String = "",// untuk artikel
    val youtubeUrl: String = "" // untuk video
)
