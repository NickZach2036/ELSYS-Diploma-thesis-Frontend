package com.example.elsysdiplomathesisfrontend.ui.feature.landmarkbyuserscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elsysdiplomathesisfrontend.domain.repository.LandmarkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LandmarkByUserViewModel(private val landmarkRepository: LandmarkRepository) : ViewModel() {
    private val _landmarkByUserData = MutableStateFlow(LandmarkByUserData())
    val landmarkByUserData = _landmarkByUserData.asStateFlow()

    fun updateName(name: String) {
        _landmarkByUserData.update { state -> state.copy(name = name) }
    }

    fun updateDescription(description: String) {
        if (description.length < 300) {
            _landmarkByUserData.update { state -> state.copy(description = description) }
        }
    }

    fun userAddLandmark() {
        viewModelScope.launch {
            landmarkRepository.userPostLandmark(
                name = _landmarkByUserData.value.name,
                description = _landmarkByUserData.value.description,
                location = "42 23",
                stationId = _landmarkByUserData.value.stationId
            )
        }
    }

    fun onVisibilityDropdownMenu(isDropdownVisible: Boolean) {
        _landmarkByUserData.update { state -> state.copy(isDropdownVisible = isDropdownVisible) }
    }

    fun onDropClick (stationId: Int) {
        _landmarkByUserData.update { state -> state.copy(stationId = stationId) }
    }
}
