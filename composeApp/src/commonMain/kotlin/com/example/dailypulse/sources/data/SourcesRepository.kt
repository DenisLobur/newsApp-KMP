package com.example.dailypulse.sources.data

class SourcesRepository(
    private val dataSource: SourcesDataSource,
    private val service: SourcesService
) {
    suspend fun getSources(forceFetch: Boolean): List<SourcesRaw> {
        if (forceFetch) {
            dataSource.clearSources()
            fetchSources()
        }

        val sourceDB = dataSource.getAllSources()
        println("Get ${sourceDB.size} from the database")

        if (sourceDB.isEmpty()) {
            fetchSources()
        }

        return sourceDB
    }

    private suspend fun fetchSources(): List<SourcesRaw> {
        val fetchedSources = service.fetchSources()
        dataSource.insertSources(fetchedSources)
        return fetchedSources
    }
}