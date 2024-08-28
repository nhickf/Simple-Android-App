package com.grpcx.androidtask.domain.viewmodel

import com.grpcx.androidtask.MainDispatcherRule
import com.grpcx.androidtask.data.MockValues
import com.grpcx.androidtask.data.repository.FakeRepositoryImpl
import com.grpcx.androidtask.domain.repository.MainRepository
import com.grpcx.androidtask.domain.usecase.FetchLatestData
import com.grpcx.androidtask.domain.usecase.MainUseCase
import com.grpcx.androidtask.domain.usecase.ObserveCacheData
import com.grpcx.androidtask.presentation.main.MainViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*


class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var useCases: MainUseCase
    private lateinit var fakeRepository: MainRepository

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        fakeRepository = FakeRepositoryImpl()
        useCases = MainUseCase(
            fetchLatestData = FetchLatestData(fakeRepository),
            observeCacheData = ObserveCacheData(fakeRepository)
        )
        viewModel = MainViewModel(useCases)
    }

    @Test
    fun check_main_state_has_emotion() = runTest {
        assertNotSame(viewModel.mainUiState.first(),MockValues.emptyMainUiState)
    }

    @Test
    fun does_observe_data_changes() = runTest {
        fakeRepository.fetchAndCacheLatestData()
        assertEquals(viewModel.mainUiState.first(),MockValues.mockkMainUiState)
    }

}