package com.example.elsysdiplomathesisfrontend.ui.feature.landmarkbyuserscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LandmarkByUserViewModel() : ViewModel() {
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
}