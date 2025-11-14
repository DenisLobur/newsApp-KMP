package com.example.dailypulse.sources.data

import com.example.dailypulse.db.DailyPulseDatabase

class SourcesDataSource(private val database: DailyPulseDatabase) {
    fun getAllSources(): List<SourcesRaw> = database.dailyPulseDatabaseQueries.selectAllSources(::mapToSourceRaw).executeAsList()

    fun insertSources(sources: List<SourcesRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            sources.forEach { sourceRaw ->
                insertSource(sourceRaw)
            }
        }
    }

    fun clearSources() = database.dailyPulseDatabaseQueries.removeAllSources()

    private fun insertSource(sourceRaw: SourcesRaw) {
        database.dailyPulseDatabaseQueries.insertSource(
            name = sourceRaw.name,
            url = sourceRaw.url,
            icon = sourceRaw.icon,
            desc = sourceRaw.desc,
            category = sourceRaw.category.joinToString(","),
            language = sourceRaw.language.joinToString(","),
            country = sourceRaw.country.joinToString(","),
            totalArticles = sourceRaw.totalArticles.toLong()
        )
    }

    private fun mapToSourceRaw(
        name: String,
        url: String,
        icon: String,
        desc: String,
        category: String,
        language: String,
        country: String,
        totalArticles: Long
    ): SourcesRaw = SourcesRaw(
        name = name,
        url = url,
        icon = icon,
        desc = desc,
        category = category.split(","),
        language = language.split(","),
        country = country.split(","),
        totalArticles = totalArticles.toInt()
    )
}