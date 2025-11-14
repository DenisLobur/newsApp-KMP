package com.example.dailypulse.sources.data

import com.example.dailypulse.sources.presentation.data.SourcesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesService(private val httpClient: HttpClient) {
    private val country = "us"
    private val apiKey = "pub_afdd32f6712546839f0ad2523fcd06e1"

    suspend fun fetchSources(): List<SourcesRaw> {
        val response: SourcesResponse =
            httpClient.get("https://newsdata.io/api/1/sources?apikey=$apiKey&country=$country").body()

        return response.sources
    }
}