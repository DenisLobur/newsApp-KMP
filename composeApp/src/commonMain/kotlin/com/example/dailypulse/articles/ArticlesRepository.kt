package com.example.dailypulse.articles

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {
    suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw> {
        if (forceFetch) {
            dataSource.clearArticles()
            fetchArticles()
        }

        val articleDB = dataSource.getAllArticles()
        println("Get ${articleDB.size} from the database")

        if (articleDB.isEmpty()) {
            fetchArticles()
        }

        return articleDB
    }

    private suspend fun fetchArticles(): List<ArticleRaw> {
        val fetchedArticles = service.fetchArticles()
        dataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}