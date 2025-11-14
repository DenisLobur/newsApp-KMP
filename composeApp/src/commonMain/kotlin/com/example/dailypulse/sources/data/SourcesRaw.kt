package com.example.dailypulse.sources.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourcesRaw(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
    @SerialName("icon")
    val icon: String,
    @SerialName("description")
    val desc: String,
    @SerialName("category")
    val category: List<String>,
    @SerialName("language")
    val language: List<String>,
    @SerialName("country")
    val country: List<String>,
    @SerialName("total_article")
    val totalArticles: Int,
)