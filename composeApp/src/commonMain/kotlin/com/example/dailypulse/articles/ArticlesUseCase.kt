package com.example.dailypulse.articles

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class ArticlesUseCase(private val repo: ArticlesRepository) {

    suspend fun getArticles(forceFetch: Boolean): List<Article> {
        val articlesRaw = repo.getArticles(forceFetch)
        return mapArticles(articlesRaw)
    }

    private fun mapArticles(articlesRaw: List<ArticleRaw>): List<Article> {
        return articlesRaw.map { raw ->
            Article(
                raw.title,
                raw.desc ?: "Click to find out more",
                getDaysAgoString(raw.date),
                raw.imageUrl ?: "No image"
            )
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun getDaysAgoString(date: String): String {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        
        // Parse date format "2025-11-11 09:56:55" to LocalDateTime
        val articleDate = try {
            val dateTimeParts = date.split(" ")
            val dateParts = dateTimeParts[0].split("-")
            val timeParts = dateTimeParts.getOrNull(1)?.split(":") ?: listOf("0", "0", "0")
            
            LocalDateTime(
                year = dateParts[0].toInt(),
                monthNumber = dateParts[1].toInt(),
                dayOfMonth = dateParts[2].toInt(),
                hour = timeParts[0].toInt(),
                minute = timeParts[1].toInt(),
                second = timeParts[2].toInt()
            ).date
        } catch (e: Exception) {
            return date // Return original date if parsing fails
        }
        
        // Calculate days difference: negative means past, positive means future
        val days = articleDate.daysUntil(today)

        val result = when {
            days == 0 -> "today"
            days == 1 -> "yesterday"
            days > 1 -> "$days days ago"
            days == -1 -> "tomorrow"
            else -> "${-days} days from now"
        }

        return result
    }
}