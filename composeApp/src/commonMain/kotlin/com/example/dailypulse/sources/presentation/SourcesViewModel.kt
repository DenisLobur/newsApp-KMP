package com.example.dailypulse.sources.presentation

import com.example.dailypulse.BaseViewModel
import com.example.dailypulse.sources.application.SourcesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SourcesViewModel(private val useCase: SourcesUseCase): BaseViewModel() {

    private val _sourcesState: MutableStateFlow<SourcesState> = MutableStateFlow(SourcesState(loading = true))
    val sourcesState: StateFlow<SourcesState> get() = _sourcesState

    init {
        getSources()
    }

    private fun getSources(forceFetch: Boolean = false) {
        scope.launch {
            _sourcesState.emit(SourcesState(loading = true, sources = _sourcesState.value.sources))
            delay(1000)
            _sourcesState.emit(SourcesState(sources = useCase.getSources(forceFetch)))
        }
    }
}