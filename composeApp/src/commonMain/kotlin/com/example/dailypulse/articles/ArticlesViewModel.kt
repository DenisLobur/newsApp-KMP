package com.example.dailypulse.articles

import com.example.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val useCase: ArticlesUseCase
) : BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticleState> = MutableStateFlow(ArticleState(loading = true))
    val articleState: StateFlow<ArticleState> get() = _articleState

    private val isMock = false

    init {
        getArticles()
    }

    fun getArticles(forceFetch: Boolean = false) {
        scope.launch {
//            delay(3000)
//
//            _articleState.emit(ArticleState(error = "Something went wrong"))

            _articleState.emit(ArticleState(loading = true, articles = _articleState.value.articles))
            delay(1000)
            val fetchedArticles = fetchArticles(forceFetch)

//            delay(1500)

            _articleState.emit(ArticleState(articles = fetchedArticles))
        }
    }

    suspend fun fetchArticles(forceFetch: Boolean): List<Article> {
        return if (isMock) {
            mockArticles
        } else {
            useCase.getArticles(forceFetch)
        }
    }

    private val mockArticles = listOf(
        Article(
            "Forest Biodiversity",
            "Exploring the rich biodiversity found in forest ecosystems around the world.",
            "2024-01-15",
            "https://images.unsplash.com/photo-1448375240586-882707db888b"
        ),
        Article(
            "Morning in the Woods",
            "The serene beauty of forests during early morning hours.",
            "2024-01-20",
            "https://images.unsplash.com/photo-1511497584788-876760111969"
        ),
        Article(
            "Ancient Trees",
            "Discovering ancient forest ecosystems and their importance to our planet.",
            "2024-01-25",
            "https://images.unsplash.com/photo-1542273917363-3b1817f69a2d"
        )
    )
}