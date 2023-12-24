package com.example.medfinder.ui

import androidx.lifecycle.ViewModel
import com.example.medfinder.Datas.OrderUiState
import com.example.medfinder.model.Meds
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()
    private var busket = mutableListOf<Meds>()
    fun addBusket(meds: Meds) {

        busket.add(meds)

    }
    fun getBusket():List<Meds>{
        return busket
    }
    fun clearBusket(){
        busket.clear()

    }
}