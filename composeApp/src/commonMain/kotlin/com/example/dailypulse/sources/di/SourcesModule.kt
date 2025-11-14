package com.example.dailypulse.sources.di

import com.example.dailypulse.sources.presentation.SourcesViewModel
import com.example.dailypulse.sources.application.SourcesUseCase
import com.example.dailypulse.sources.data.SourcesDataSource
import com.example.dailypulse.sources.data.SourcesRepository
import com.example.dailypulse.sources.data.SourcesService
import org.koin.dsl.module

val sourcesModule = module {
    single<SourcesService> { SourcesService(get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesDataSource> { SourcesDataSource(get()) }
    single<SourcesRepository> { SourcesRepository(get(), get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
}