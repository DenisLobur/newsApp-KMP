package com.example.dailypulse.articles.presentation

import com.example.dailypulse.articles.application.Article

data class ArticleState(
    val articles: List<Article> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)