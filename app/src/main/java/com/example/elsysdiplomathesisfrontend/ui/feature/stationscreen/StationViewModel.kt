package com.example.elsysdiplomathesisfrontend.ui.feature.stationscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elsysdiplomathesisfrontend.data.model.Station
import com.example.elsysdiplomathesisfrontend.data.service.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StationViewModel(
    private val authService: AuthService
) : ViewModel() {

    private val _stationData = MutableStateFlow(StationData())
    val stationData = _stationData.asStateFlow()

    fun loadStationData(stationId: Int) {
        viewModelScope.launch {
            try {
                val result = authService.getLandmarks(stationId)
                _stationData.update { state ->
                    state.copy(
                        landmarks = result,
                        station = result.getOrNull(0)?.Station ?: Station()
                    )
                }
            } catch (e: Exception) {
                _stationData.update { state ->
                    state.copy(
                        error = e.message ?: "Unknown error!"
                    )
                }
            }
        }
    }
}
