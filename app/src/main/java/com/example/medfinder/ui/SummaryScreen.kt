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
package com.example.cupcake.ui


import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medfinder.R
import com.example.medfinder.ui.BusketSubmit
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * This composable expects [orderUiState] that represents the order state, [onCancelButtonClicked]
 * lambda that triggers canceling the order and passes the final order to [onSendButtonClicked]
 * lambda
 */
@Composable
fun SummaryScreen(
    onCancelButtonClicked: () -> Unit,
    onSendButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        ContactsBlock()
        AdressBlock()
        DeliveryVariant()
        TimeBlock()
        Row(modifier = Modifier.padding(10.dp)) {
            BusketSubmit()
        }
    }
}


@Composable
fun TimeBlock() {
    var dateInput by remember { mutableStateOf("") }
    var checkedState_5 by remember { mutableStateOf(true) }
    var checkedState_6 by remember { mutableStateOf(false) }
    var checkedState_7 by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BlockTitle(titleName = R.string.title_block4)

        showDatePicker()

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            CustomCheckbox(
                checked = checkedState_5,
                onCheckedChange = {
                    checkedState_5 = true
                    checkedState_6 = false
                    checkedState_7 = false
                },
                checkBoxName = R.string.checkbox_5,
                Modifier.weight(1f)
            )

            CustomCheckbox(
                checked = checkedState_6,
                onCheckedChange = {
                    checkedState_5 = false
                    checkedState_6 = true
                    checkedState_7 = false
                },
                checkBoxName = R.string.checkbox_6,
                modifier = Modifier.weight(1f)
            )

            CustomCheckbox(
                checked = checkedState_7,
                onCheckedChange = {
                    checkedState_5 = false
                    checkedState_6 = false
                    checkedState_7 = true
                },
                checkBoxName = R.string.checkbox_7,
                modifier = Modifier.weight(1f)
            )


        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showDatePicker() {
    val calendar = Calendar.getInstance()
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var selectedDate by remember {
        mutableStateOf(calendar.timeInMillis)
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    selectedDate = datePickerState.selectedDateMillis!!
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }

    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        shape = RoundedCornerShape(17.dp),
        border = BorderStroke(1.dp, Color(0xFFE8E8E8)),
        color = Color.White

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Text(text = "*Ваша дата: ${formatter.format(Date(selectedDate))}",
                fontSize = 18.sp
            )
            Button(onClick = {
                    showDatePicker = true
                },
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
            ) {

            }
        }
    }


}

@Composable
fun DeliveryVariant() {
    var deliveryInput by remember { mutableStateOf("") }
    var checkedState_1 by remember { mutableStateOf(true) }
    var checkedState_2 by remember { mutableStateOf(false) }
    var checkedState_3 by remember { mutableStateOf(false) }
    var checkedState_4 by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BlockTitle(titleName = R.string.title_block3)

        CustomCheckbox(
            checked = checkedState_1,
            onCheckedChange = {
                checkedState_1 = true
                checkedState_2 = false
                checkedState_3 = false
                checkedState_4 = false
            },
            checkBoxName = R.string.checkbox_1,
            modifier = Modifier.fillMaxWidth()
        )

        CustomCheckbox(
            checked = checkedState_2,
            onCheckedChange = {
                checkedState_1 = false
                checkedState_2 = true
                checkedState_3 = false
                checkedState_4 = false
            },
            checkBoxName = R.string.checkbox_2,
            modifier = Modifier.fillMaxWidth()
        )

        CustomCheckbox(
            checked = checkedState_3,
            onCheckedChange = {
                checkedState_1 = false
                checkedState_2 = false
                checkedState_3 = true
                checkedState_4 = false
            },
            checkBoxName = R.string.checkbox_3,
            modifier = Modifier.fillMaxWidth()
        )

        CustomCheckbox(
            checked = checkedState_4,
            onCheckedChange = {
                checkedState_1 = false
                checkedState_2 = false
                checkedState_3 = false
                checkedState_4 = true
            },
            checkBoxName = R.string.checkbox_4,
            modifier = Modifier.fillMaxWidth()
        )

    }
}

@Composable
fun CustomCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    @StringRes checkBoxName: Int,
    modifier: Modifier = Modifier

) {

    Surface(
        shape = RoundedCornerShape(13.dp),
        color = if (checked) Color(0xFF004B81004B81) else Color.Transparent,
        border = BorderStroke(1.dp, Color(0xFF004B81)),
        modifier = Modifier
            .padding(bottom = 15.dp)
            .clickable { onCheckedChange(!checked) }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Text(text = stringResource(checkBoxName),
                color = if (checked) Color.White else Color(0xFF004B81004B81),
                fontSize = 18.sp
            )
        }
    }

}

@Composable
fun AdressBlock() {
    var cityInput by remember { mutableStateOf("") }
    var streetInput by remember { mutableStateOf("") }
    var houseInput by remember { mutableStateOf("") }
    var appartmentInput by remember { mutableStateOf("") }
    var levelInput by remember { mutableStateOf("") }
    var addresInfoInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BlockTitle(titleName = R.string.title_block2)
        EditField(
            label = R.string.label_3,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            value = cityInput,
            onValueChanged = { cityInput = it },
            modifier = Modifier
                .padding(bottom = 15.dp)
                .fillMaxWidth(),
        )
        EditField(
            label = R.string.label_4,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            value = streetInput,
            onValueChanged = { streetInput = it },
            modifier = Modifier
                .padding(bottom = 15.dp)
                .fillMaxWidth(),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            EditField(
                label = R.string.label_5,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                value = houseInput,
                onValueChanged = { houseInput = it },
                modifier = Modifier.weight(1f),
            )
            EditField(
                label = R.string.label_6,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                value = appartmentInput,
                onValueChanged = { appartmentInput = it },
                modifier = Modifier.weight(2f),
            )

            EditField(
                label = R.string.label_7,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                value = levelInput,
                onValueChanged = { levelInput = it },
                modifier = Modifier.weight(1f),
            )

        }

        EditBigField(
            label = R.string.label_8,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            value = addresInfoInput,
            onValueChanged = { addresInfoInput = it },
            modifier = Modifier
                .padding(bottom = 15.dp)
                .fillMaxWidth(),
        )
    }
}

@Composable
fun ContactsBlock() {
    var nameInput by remember { mutableStateOf("") }
    var phoneInput by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        BlockTitle(titleName = R.string.title_block1)
        EditField(
            label = R.string.label_1,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            value = nameInput,
            onValueChanged = { nameInput = it },
            modifier = Modifier
                .padding(bottom = 15.dp)
                .fillMaxWidth(),
        )
        EditField(
            label = R.string.label_2,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            value = phoneInput,
            onValueChanged = { phoneInput = it },
            modifier = Modifier
                .padding(bottom = 15.dp)
                .fillMaxWidth(),
        )

    }
}

@Composable
fun BlockTitle(
    @StringRes titleName: Int,
    modifier: Modifier = Modifier
){
    Text(text = stringResource(titleName),
        color = Color(0xFF004B81),
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 17.dp),
        fontSize = 26.sp,

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
        shape = RoundedCornerShape(17.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF66AFE3),
            unfocusedBorderColor = Color(0xFFE8E8E8),
            containerColor = Color.White
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBigField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        singleLine = false,
        modifier = modifier.height(200.dp),
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
        shape = RoundedCornerShape(17.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF66AFE3),
            unfocusedBorderColor = Color(0xFFE8E8E8),
            containerColor = Color.White
        )
    )
}

@Preview
@Composable
fun OrderSummaryPreview(){
    SummaryScreen(
        onSendButtonClicked = {},
        onCancelButtonClicked = {},
        modifier = Modifier.fillMaxHeight()
    )
}
