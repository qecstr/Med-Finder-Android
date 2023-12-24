package com.example.medfinder.ui.Screens




import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import com.example.medfinder.model.MenuItem

object NavItems{
   val items = listOf(
       MenuItem(
           id = 1,
           title = "Catalog",
           contentDescription = "Go to home screen",
           icon = Icons.Default.Home
       ),
       MenuItem(
           id = 3,
           title = "Busket",
           contentDescription = "Go to settings screen",
           icon = Icons.Default.Settings
       )
   )
}