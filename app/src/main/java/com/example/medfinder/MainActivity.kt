package com.example.medfinder


import androidx.compose.foundation.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.medfinder.model.Meds
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.medfinder.Data.DefaultData
import com.example.medfinder.model.MenuItem
import com.example.medfinder.ui.Screens.AppBar


import com.example.medfinder.ui.Screens.MedsUiState
import com.example.medfinder.ui.Screens.MedsViewModel
import com.example.medfinder.ui.Screens.NavItems
import com.example.medfinder.ui.theme.MedFinderTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedFinderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.back)
                ) {
                  val marsViewModel: MedsViewModel = viewModel()

                    MenuBar(medsUiState = marsViewModel.medsUiState, modifier = Modifier)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedItem(
   meds: Meds,
   modifier: Modifier
){
    Card(
        modifier = Modifier
            .padding(16.dp, 16.dp, 16.dp, 0.dp)
            .shadow(
                20.dp,
                shape = RoundedCornerShape(8.dp),

                ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.border),
        ),
        onClick = {}
    ){
    Row (verticalAlignment = Alignment.CenterVertically){
        Box {
            if(meds.MedImage == null || meds.MedImage == "") {
                Image(

                    painter = painterResource(R.drawable.ic_launcher_foreground),

                    contentDescription = meds.MedId,
                    //contentScale = ContentScale.Crop
                )
            }else{
                AsyncImage(
                    model = meds.MedImage,
                    contentDescription = stringResource(id = meds.MedId!!.toInt()),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,

            ){

            Text(
                text = meds.MedName!!,//LocalContext.current.getString(),
                modifier = modifier.padding(16.dp,32.dp,16.dp,8.dp),

                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.orange)

            )
            Row() {

                Text(
                    text = meds.MedPrice!!,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = modifier.padding( 0.dp,0.dp,20.dp,8.dp),
                    color = colorResource(id = R.color.price_colour)
                )

            }

        }


    }
        Button(onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.button)
                )

            ) {
                Text(text = "Добавить в корзину")
        }
}

}
@Composable
fun MedFinder(meds: List<Meds>, modifier: Modifier = Modifier) {

    var currentScreen by remember { mutableStateOf("catalog") }
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(10){item ->
            MedItem(meds = meds[item], modifier =modifier )

        }
        }
    }
@Composable
fun HomeScreen(
  medsUiState: MedsUiState, modifier: Modifier = Modifier,contentPadding: PaddingValues = PaddingValues(0.dp)
) { when (medsUiState) {
    is MedsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
    is MedsUiState.Success -> MedFinder(
    medsUiState.meds, modifier = modifier.fillMaxWidth()
    )

    is MedsUiState.Error -> ErrorScreen(  modifier = modifier.fillMaxSize())
}}
@Composable
fun ResultScreen(meds: ArrayList<Meds>, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = meds[0].MedName.toString())
    }
}
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Text(text = "loading")
}
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {

        Text(text =" errro")
    }

@Composable
fun Med(meds:Meds, modiier:Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
        ) {
        Text(
            text = meds.MedName!!,
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(id = R.color.orange),
            modifier = Modifier
                .padding(top = 12.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.button)
            )
        )
        {
            Text(text = "Добавить в корзину")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row (verticalAlignment = Alignment.CenterVertically){
            Card(
                modifier = Modifier
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
                    .shadow(20.dp, shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth(),
               colors = CardDefaults.cardColors(
                   containerColor = colorResource(id = R.color.border),)
            )
            {
                Text(
                    text = "Категория:       " + meds.MedCategory,
                    modifier = Modifier.padding(12.dp,12.dp,12.dp,12.dp)
                    )
                Text(
                    text = "Цена:             " + meds.MedPrice,
                    modifier = Modifier.padding(12.dp,12.dp,12.dp,12.dp)
                )
                var receipt = meds.MedRecept
                if(receipt == null || receipt == ""){
                    receipt = "Без Рецепта"
                }
                Text(
                    text = "Рецепт:          " + receipt,
                    modifier = Modifier.padding(12.dp,12.dp,12.dp,12.dp)
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBar(medsUiState:MedsUiState, modifier: Modifier){
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
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header", fontSize = 60.sp)
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MedFinderPreview() {
    MedFinderTheme {

        //MedFinder(meds = DefaultData.q)
       // Med(meds = DefaultData.q[0])
        val a = MedsUiState.Success(DefaultData.q)
        MenuBar(medsUiState = a, modifier = Modifier)
    }
}