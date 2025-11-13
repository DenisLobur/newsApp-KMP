package com.example.dailypulse.articles

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {
    suspend fun getArticles(): List<ArticleRaw> {
        val articleDB = dataSource.getAllArticles()
        println("Get ${articleDB.size} from the database")

        if (articleDB.isEmpty()) {
            val fetchedArticles = service.fetchArticles()
            dataSource.insertArticles(fetchedArticles)
            return fetchedArticles
        }

        return articleDB
    }
}