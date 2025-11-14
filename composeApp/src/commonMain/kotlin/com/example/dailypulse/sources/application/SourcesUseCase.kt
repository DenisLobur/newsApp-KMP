package com.example.dailypulse.sources.application

import com.example.dailypulse.sources.data.SourcesRaw
import com.example.dailypulse.sources.data.SourcesRepository

class SourcesUseCase(private val repo: SourcesRepository) {
    suspend fun getSources(forceFetch: Boolean): List<Source> {
        val sourcesRaw = repo.getSources(forceFetch)
        return mapSources(sourcesRaw)
    }

    private fun mapSources(sourcesRaw: List<SourcesRaw>): List<Source> {
        return sourcesRaw.map { raw ->
            Source(
                raw.name,
                raw.url,
                raw.icon,
                raw.desc,
                raw.category,
                raw.language,
                raw.country,
                raw.totalArticles
            )
        }
    }


}