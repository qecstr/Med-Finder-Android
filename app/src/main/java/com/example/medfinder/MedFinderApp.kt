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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.medfinder.datas.DataSource
import com.example.medfinder.ui.BusketOrderScreen
import com.example.medfinder.ui.HomeScreen
import com.example.medfinder.ui.OrderViewModel
import com.example.medfinder.ui.Screens.AppBar
import com.example.medfinder.ui.Screens.MedsUiState
import com.example.medfinder.ui.Screens.NavItems
import com.example.medfinder.ui.currentMedViewModel
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
fun MenuBar(
    medsUiState: MedsUiState,
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: currentMedViewModel = viewModel()
    ){
    val drawerState =  rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = NavItems.items
    val backStackEntry by navController.currentBackStackEntryAsState()
    val selectedItem = remember { mutableStateOf(items[0]) }
    val order:OrderViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
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
                            navController.navigate(item.title)
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
                innerPadding ->
            val uiState by viewModel.uiState.collectAsState()
            NavHost(
                navController = navController,
                startDestination = MedFinderScreen.Catalog.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = MedFinderScreen.Catalog.name) {
                    HomeScreen(medsUiState = medsUiState,modifier = Modifier,onCardClick = { viewModel.setMeds(it);navController.navigate(MedFinderScreen.MedItem.name)})
                }
                composable(route = MedFinderScreen.MedItem.name ) {
                    Med(meds = viewModel.getMeds())

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
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedFinderApp (
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MedFinderScreen.valueOf(
        backStackEntry?.destination?.route ?: MedFinderScreen.Catalog.name
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
            startDestination = MedFinderScreen.Catalog.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MedFinderScreen.Catalog.name) {
                BusketOrderScreen(
                    quantityOptions = DataSource.quantityOptions,
                    onNextButtonClicked = {
                        navController.navigate(MedFinderScreen.MedItem.name)
                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = MedFinderScreen.MedItem.name) {


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
    Catalog(title = 1),
    MedItem(title = 2),
    Pickup(title = 3),
    Summary(title = 4)
}