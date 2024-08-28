package com.grpcx.androidtask.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.grpcx.androidtask.presentation.theme.AppTheme
import com.grpcx.androidtask.domain.models.States
import com.grpcx.androidtask.presentation.components.Activities
import com.grpcx.androidtask.presentation.components.Level
import com.grpcx.androidtask.presentation.components.MainTopBar
import com.grpcx.androidtask.presentation.components.WeeklyCalendar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val state by mainViewModel.mainUiState.collectAsStateWithLifecycle()
                var isRefreshing by remember { mutableStateOf(false) }
                val refreshState = rememberPullToRefreshState()
                val coroutineScope = rememberCoroutineScope()
                val onRefresh: () -> Unit = {
                    isRefreshing = true
                    coroutineScope.launch {
                        delay(1500)
                        mainViewModel.onFetchLatestData()
                        isRefreshing = false
                    }
                }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = {
                        Column(
                            modifier = Modifier.padding(
                                vertical = 8.dp
                            )
                        ) {
                            MainTopBar()
                            WeeklyCalendar()
                        }
                    },
                    content = { innerPadding ->

                        PullToRefreshBox(
                            state = refreshState,
                            modifier = Modifier.padding(innerPadding),
                            isRefreshing = isRefreshing,
                            onRefresh = onRefresh,
                            indicator = {
                                Indicator(
                                    modifier = Modifier.align(Alignment.TopCenter),
                                    isRefreshing = isRefreshing,
                                    state = refreshState,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        ) {
                            LazyVerticalGrid(
                                modifier = Modifier.fillMaxSize(),
                                columns = GridCells.Fixed(2),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalArrangement = Arrangement.Center,
                                contentPadding = PaddingValues(16.dp)
                            ) {

                                state.emotion.levels.forEachIndexed { index, level ->
                                    val activities = level.activities

                                    item(
                                        key = index,
                                        span = { GridItemSpan(2) },
                                        contentType = "level"
                                    ) {
                                        Level(level = level)
                                    }

                                    items(
                                        items = activities,
                                        span = { activity ->
                                            val isFullSpan = activity == activities.last() &&
                                                    activities.size % 2 == 1
                                            GridItemSpan(
                                                when (isFullSpan) {
                                                    true -> 2
                                                    false -> 1
                                                }
                                            )
                                        }
                                    ) {
                                        Activities(
                                            isLocked = level.state == States.LOCKED,
                                            activity = it
                                        )
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting("Android")
    }
}