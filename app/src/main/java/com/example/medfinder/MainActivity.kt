package com.example.medfinder


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.medfinder.model.Meds
import com.example.medfinder.ui.HomeScreen
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
                    color = colorResource(id = R.color.back)
                ) {
                    val marsViewModel: MedsViewModel = viewModel()
                    HomeScreen(medsUiState = marsViewModel.medsUiState, modifier = Modifier)
                }
            }
        }
    }
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedItem(
   meds: Meds,
   modifier: Modifier,
   onCardClick:(Meds) -> Unit = {}
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
        onClick = { onCardClick(meds)}
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedFinder(meds: List<Meds>, modifier: Modifier = Modifier, onCardClick:(Meds) -> Unit = {}) {
    var temp by remember { mutableStateOf(meds) }

    var text by remember { mutableStateOf("") }
    var active by remember {
        mutableStateOf(false)
    }
    var searchedItems = remember {
        mutableListOf("")
    }
      Column() {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                searchedItems.add(text)
                active = false
                temp = search(meds, text)
                text = ""
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text(text = "Поиск") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable { text = "" },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon"
                    )

                } else {
                  active = false
                }
            },
        ) {

        }
          LazyVerticalGrid(
              columns = GridCells.Fixed(1),
              modifier = modifier,
              verticalArrangement = Arrangement.spacedBy(8.dp),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
              items(temp){med ->
                  MedItem(meds =med, modifier = modifier, onCardClick = onCardClick)
              }

          }

    }


}
fun search(meds:List<Meds>,text:String):List<Meds>{
  var q  = mutableListOf<Meds>()
        for(items in meds){
            if(items.MedName.contains(text)){
                q.add(items)
            }
        }
    return q
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


@Preview(showBackground = true)
@Composable
fun MedFinderPreview() {
    MedFinderTheme {

        //MedFinder(meds = DefaultData.q)
       // Med(meds = DefaultData.q[0])
       /* val a = MedsUiState.Success(DefaultData.q)
        MenuBar(medsUiState = a, modifier = Modifier)*/
    }
}