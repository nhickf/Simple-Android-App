package com.grpcx.androidtask.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grpcx.androidtask.domain.models.Emotion
import com.grpcx.androidtask.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: MainUseCase
) : ViewModel() {

    private var _mainUiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState())

    val mainUiState: StateFlow<MainUiState> = _mainUiState
        .asStateFlow()
        .onStart {
            onFetchLatestData()
        }
        .flatMapMerge {
            useCase.observeCacheData().map {
                MainUiState(it)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = MainUiState()
        )

    fun onFetchLatestData() {
        viewModelScope.launch {
            useCase.fetchLatestData()
        }
    }
}

data class MainUiState(
    val emotion: Emotion = Emotion(emptyList())
)



