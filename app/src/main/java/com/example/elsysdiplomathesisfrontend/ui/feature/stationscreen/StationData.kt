package com.example.elsysdiplomathesisfrontend.ui.feature.stationscreen

import com.example.elsysdiplomathesisfrontend.data.model.Comment
import com.example.elsysdiplomathesisfrontend.data.model.Landmark
import com.example.elsysdiplomathesisfrontend.data.model.Station

@kotlinx.serialization.Serializable
data class StationData(
    val id: String = "",
    val landmarks: List<Landmark> = listOf(),
    val station: Station = Station(),
    val error: String = "",
    val isBottomSheetVisible: Boolean = false,
    val comments: List<Comment> = listOf(),
)
