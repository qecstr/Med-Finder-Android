package com.example.medfinder.ui

import androidx.lifecycle.ViewModel
import com.example.medfinder.Datas.DefaultDataq
import com.example.medfinder.Datas.currentMed
import com.example.medfinder.model.Meds
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class currentMedViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(currentMed(meds = DefaultDataq.q[2]))
    val uiState: StateFlow<currentMed> = _uiState.asStateFlow()

    fun setMeds(desiredMeds: Meds) {
        _uiState.update { currentState ->
            currentState.copy(meds = desiredMeds)
        }
    }
    fun getMeds(): Meds {
        return _uiState.value.meds
    }
}