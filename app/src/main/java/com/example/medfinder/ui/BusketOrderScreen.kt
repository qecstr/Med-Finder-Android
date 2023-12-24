/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.medfinder.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medfinder.R
import com.example.medfinder.datas.DataSource


@Composable
fun BusketOrderScreen(
    quantityOptions: List<Pair<Int, Int>>,
    onNextButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier.background(Color(0xFFF2F8FC))

){
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Text(
                text = stringResource(R.string.busket),
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFFFF9000),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

        Row(modifier = Modifier) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.padding_medium)
                )
            ) {
                quantityOptions.forEach { item ->
                    SelectQuantityButton(
                        labelResourceId = item.first,
                        cost = item.second
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

        Row(modifier = Modifier) {
            BusketSubmit()
        }

    }
}


@Composable
fun SelectQuantityButton(
    @StringRes labelResourceId: Int,
    cost: Int,
    modifier: Modifier = Modifier
){
    Surface(
        modifier = modifier
            .widthIn(min = 290.dp)
            .height(190.dp),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(R.drawable.aspirin),
                    contentDescription = stringResource(R.string.image_desc),
                    modifier = modifier
                        .background(color = Color.Black)
                        .width(100.dp)
                        .height(100.dp)
                )
                Spacer(modifier = modifier.width(25.dp))
                Text(
                    stringResource(labelResourceId),
                    color = Color(0xFFFF9000),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    cost.toString() + " тг",
                    color = Color(0xFFA65E00),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row() {
                    TextButton(
                        onClick = { /*TODO*/ },
                        modifier = modifier
                            .width(40.dp)
                            .height(40.dp),
                        shape = RoundedCornerShape(13.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color(0xFF004B81),
                            contentColor = Color.White
                        )
                    ) {
                        Text("-")
                    }
                    Spacer(modifier = modifier.width(5.dp))
                    Surface(
                        shape = RoundedCornerShape(13.dp),
                        color = Color.White,
                        border = BorderStroke(1.dp, Color(0xFF004B81)),
                        modifier = modifier
                            .width(40.dp)
                            .height(40.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("1", color = Color(0xFF66AFE3))
                        }
                    }
                    Spacer(modifier = modifier.width(5.dp))

                    TextButton(
                        onClick = { /*TODO*/ },
                        modifier = modifier
                            .width(40.dp)
                            .height(40.dp),
                        shape = RoundedCornerShape(13.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color(0xFF004B81),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "+")
                    }
                }
            }

        }

    }
}

@Composable
fun BusketSubmit(
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier
            .widthIn(min = 290.dp)
            .heightIn(min = 100.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color(0xFFD9D9D9)),
        color = Color.Transparent
    ) {
        Spacer(modifier = modifier.height(10.dp))

        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ваша корзина:",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFFFF9000),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = modifier.height(10.dp))
            Text("Внимание! Доставка осуществляется при наличии рецепта на рецептурные препараты!",
                color = Color(0xFF6A6A6A),
                fontSize = 18.sp,
                modifier = modifier.width(290.dp)

            )
            Spacer(modifier = modifier.height(25.dp))
            Column() {
                Row(
                    modifier = modifier.width(290.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Всего товаров:",
                        fontSize = 20.sp,
                        color = Color(0xFF434A54)

                    )
                    Text(text = "2 шт",
                        fontSize = 20.sp,
                        color = Color(0xFF66AFE3)

                    )
                }

                Row(
                    modifier = modifier.width(290.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Сумма заказа:",
                        fontSize = 24.sp,
                        color = Color(0xFF434A54),
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "2620 тг",
                        fontSize = 24.sp,
                        color = Color(0xFFFF9000),
                        fontWeight = FontWeight.Bold

                    )
                }
            }

            Spacer(modifier = modifier.height(20.dp))

            TextButton(
                onClick = { /*TODO*/ },
                modifier = modifier
                    .width(290.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color(0xFF890000),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Заказать",
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = modifier.height(10.dp))
        }
    }
}

@Preview
@Composable
fun BusketOrderPreview(){

    BusketOrderScreen(
        quantityOptions = DataSource.quantityOptions,
        onNextButtonClicked = {},
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F8FC))
            .padding(dimensionResource(R.dimen.padding_medium))
    )
}
