package com.example.medfinder.model

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: Int,
    val title: String,
    val contentDescription: String,
    val icon: ImageVector
)