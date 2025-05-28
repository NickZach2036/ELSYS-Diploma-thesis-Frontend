package com.example.elsysdiplomathesisfrontend.ui.feature.stationscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elsysdiplomathesisfrontend.data.model.Station
import com.example.elsysdiplomathesisfrontend.domain.repository.CommentRepository
import com.example.elsysdiplomathesisfrontend.domain.repository.LandmarkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StationViewModel(
    private val landmarkRepository: LandmarkRepository,
    private val commentRepository: CommentRepository,
) : ViewModel() {

    private val _stationData = MutableStateFlow(StationData())
    val stationData = _stationData.asStateFlow()

    fun loadStationData(stationId: Int) {
        viewModelScope.launch {
            try {
                val result = landmarkRepository.getStation(stationId)
                _stationData.update { state ->
                    state.copy(
                        landmarks = result.getOrNull() ?: emptyList(),
                        station = result.getOrNull()?.getOrNull(0)?.Station ?: Station()
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

    fun bottomSheetVisibility(isBottomSheetVisible: Boolean, landmarkId: Int) {
        _stationData.update { state -> state.copy(isBottomSheetVisible = isBottomSheetVisible) }

        if(isBottomSheetVisible) {
             loadComments(landmarkId)
        }
    }

    fun loadComments(commentId: Int) {
        viewModelScope.launch {
            try {
                val result = commentRepository.getCommentsForLandmark(commentId)
                if (result.isSuccess) {
                    result.getOrNull()?.let { comments ->
                        _stationData.update { state ->
                            state.copy(comments = comments)
                        }
                    }
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
