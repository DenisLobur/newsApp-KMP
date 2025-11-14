package com.example.dailypulse.sources.presentation.di

import com.example.dailypulse.sources.presentation.SourcesViewModel
import com.example.dailypulse.sources.presentation.application.SourcesUseCase
import com.example.dailypulse.sources.presentation.data.SourcesDataSource
import com.example.dailypulse.sources.presentation.data.SourcesRepository
import com.example.dailypulse.sources.presentation.data.SourcesService
import org.koin.dsl.module

val sourcesModule = module {
    single<SourcesService> { SourcesService(get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesDataSource> { SourcesDataSource(get()) }
    single<SourcesRepository> { SourcesRepository(get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
}