package com.example.dailypulse.sources.application

data class Source(
    val name: String,
    val url: String,
    val icon: String,
    val desc: String,
    val category: List<String>,
    val language: List<String>,
    val country: List<String>,
    val totalArticles: Int
)
