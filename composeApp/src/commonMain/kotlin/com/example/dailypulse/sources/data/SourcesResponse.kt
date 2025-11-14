package com.example.dailypulse.sources.presentation.data

import com.example.dailypulse.sources.data.SourcesRaw
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourcesResponse(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int,
    @SerialName("results")
    val sources: List<SourcesRaw>
)