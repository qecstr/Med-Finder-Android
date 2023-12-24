package com.example.medfinder.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medfinder.MedFinder
import com.example.medfinder.ui.Screens.MedsUiState

@Composable
fun HomeScreen(
    medsUiState: MedsUiState, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues(0.dp)
) { when (medsUiState) {
    is MedsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
    is MedsUiState.Success -> MedFinder(
        medsUiState.meds, modifier = modifier.fillMaxWidth()
    )

    is MedsUiState.Error -> ErrorScreen(  modifier = modifier.fillMaxSize())
}}