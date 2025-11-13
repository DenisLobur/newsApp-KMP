package com.example.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val httpClient: HttpClient) {
    private val country = "us"
    private val category = "business"
    private val apiKey = "pub_afdd32f6712546839f0ad2523fcd06e1"

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticlesResponse =
            httpClient.get("https://newsdata.io/api/1/news?country=${country}&category=${category}&apikey=${apiKey}").body()

        return response.articles
    }
}