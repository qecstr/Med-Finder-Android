package com.example.medfinder.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.medfinder.MenuBar
import com.example.medfinder.ui.Screens.MedsUiState

@Composable
fun HomeScreen(
    medsUiState: MedsUiState, modifier: Modifier = Modifier
) { when (medsUiState) {
    is MedsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
    is MedsUiState.Success -> MenuBar(
        medsUiState.meds, modifier = modifier.fillMaxWidth()
    )

    is MedsUiState.Error -> ErrorScreen(  modifier = modifier.fillMaxSize())
}}