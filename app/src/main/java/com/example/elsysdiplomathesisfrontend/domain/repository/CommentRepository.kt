package com.example.elsysdiplomathesisfrontend.domain.repository

import com.example.elsysdiplomathesisfrontend.data.model.Comment

interface CommentRepository {
    suspend fun getCommentsForLandmark(landmarkId: Int): Result<List<Comment>>
}
