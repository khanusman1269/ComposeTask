package com.compose.api.task.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.api.task.common.UiState
import com.compose.api.task.domain.models.Medicine
import com.compose.api.task.domain.use_cases.MedicinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val medicinesUseCase: MedicinesUseCase): ViewModel() {

    private val _medicineState = MutableStateFlow<UiState<List<Medicine>>>(UiState.Idle)
    val medicineState: StateFlow<UiState<List<Medicine>>> = _medicineState.asStateFlow()

    init {
        getMedicines()
    }

    fun getMedicines(){
        _medicineState.value = UiState.Loading
        viewModelScope.launch {
            medicinesUseCase.getMedicines().catch {
                _medicineState.value = UiState.Error(it.message.toString())
            }.collect{medicines->
                if(medicines.isEmpty()){
                    _medicineState.value = UiState.Empty
                } else {
                    _medicineState.value = UiState.Success(medicines)
                }
            }
        }
    }

    fun clearLocalDb(){
        viewModelScope.launch(Dispatchers.IO) {
            medicinesUseCase.clearLocalDb()
        }
    }


}