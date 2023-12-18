package com.example.medfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medfinder.model.Meds
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medfinder.ui.Screens.MedsUiState
import com.example.medfinder.ui.Screens.MedsViewModel
import com.example.medfinder.ui.theme.MedFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedFinderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val marsViewModel: MedsViewModel = viewModel()
                    HomeScreen(medsUiState = marsViewModel.medsUiState)
                }
            }
        }
    }
}


@Composable
fun MedItem(
   meds: Meds,
   modifier: Modifier
){
    Card{
    Row {
        Box {

        }
        Column {
            Text(
                text = "trest",//LocalContext.current.getString(),
                modifier = Modifier.padding(16.dp,16.dp,16.dp,8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "trst",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(start = 8.dp,end = 8.dp)
                )
            }
        }
    }
}

}
@Composable
fun MedFinder(name: String, modifier: Modifier = Modifier) {

    var currentScreen by remember { mutableStateOf("catalog") }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        }
    }
@Composable
fun HomeScreen(
  medsUiState: MedsUiState, modifier: Modifier = Modifier
) { when (medsUiState) {
    is MedsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
    is MedsUiState.Success -> ResultScreen(
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MedFinderTheme {
        val marsViewModel: MedsViewModel = viewModel()
        HomeScreen(medsUiState = marsViewModel.medsUiState)
    }
}