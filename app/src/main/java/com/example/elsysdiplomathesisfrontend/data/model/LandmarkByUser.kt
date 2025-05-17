package com.example.elsysdiplomathesisfrontend.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LandmarkByUser(
    @SerialName("name")
    val name: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("location")
    val location: String?,
    @SerialName("stationId")
    val stationId: Int?
)
