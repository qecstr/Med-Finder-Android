package com.example.medfinder.Datas

import com.example.medfinder.model.Meds

data class OrderUiState (
    val busketProducts: List<Meds> = listOf<Meds>()
)