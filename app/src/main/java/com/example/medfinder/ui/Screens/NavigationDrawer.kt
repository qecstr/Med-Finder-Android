package com.example.medfinder.ui.Screens




import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medfinder.model.MenuItem

object NavItems{
   val items = listOf(
       MenuItem(
           id = "home",
           title = "Home",
           contentDescription = "Go to home screen",
           icon = Icons.Default.Home
       ),
       MenuItem(
           id = "settings",
           title = "Settings",
           contentDescription = "Go to settings screen",
           icon = Icons.Default.Settings
       ),
       MenuItem(
           id = "help",
           title = "Help",
           contentDescription = "Get help",
           icon = Icons.Default.Info
       )
   )
}