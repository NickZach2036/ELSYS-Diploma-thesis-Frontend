package com.example.elsysdiplomathesisfrontend.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    @SerialName("id")
    val id: Int?,

    @SerialName("content")
    val content: String?,

    @SerialName("userId")
    val userId: Int?,

    @SerialName("landmarkId")
    val landmarkId: Int?,

    @SerialName("createdAt")
    val createdAt: String?,

    @SerialName("User")
    val User: User?
)
