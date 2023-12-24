package com.example.medfinder

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.ui.SummaryScreen
import com.example.medfinder.data.DataSource
import com.example.medfinder.ui.BusketOrderScreen
import com.example.medfinder.ui.HomeScreen
import com.example.medfinder.ui.OrderViewModel
import com.example.medfinder.ui.Screens.AppBar
import com.example.medfinder.ui.Screens.MedsUiState
import com.example.medfinder.ui.Screens.NavItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedFinderAppBar(
    currentScreen: MedFinderScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBar(medsUiState: MedsUiState, modifier: Modifier){
    val drawerState =  rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = NavItems.items
    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { item.icon },
                        label = { Text(item.title) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }

                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }


    ) {

        Scaffold (
            topBar = {  AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            )

            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Наверх") },
                    icon = { Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "") },
                    onClick = {

                    }
                )
            }
        ){
            HomeScreen(medsUiState = medsUiState,modifier = Modifier, contentPadding = it)

        }
    }
}


@Composable
fun MedFinderApp (
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MedFinderScreen.valueOf(
        backStackEntry?.destination?.route ?: MedFinderScreen.Start.name
    )

    Scaffold(
        topBar = {
            MedFinderAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = MedFinderScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MedFinderScreen.Start.name) {
                BusketOrderScreen(
                    quantityOptions = DataSource.quantityOptions,
                    onNextButtonClicked = {
                        navController.navigate(MedFinderScreen.Flavor.name)
                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = MedFinderScreen.Flavor.name) {

            }
            composable(route = MedFinderScreen.Pickup.name) {

            }
            composable(route = MedFinderScreen.Summary.name) {
                val context = LocalContext.current
                SummaryScreen(
                    onCancelButtonClicked = {},
                    onSendButtonClicked = {},
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}


enum class MedFinderScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Flavor(title = R.string.app_name),
    Pickup(title = R.string.app_name),
    Summary(title = R.string.app_name)
}