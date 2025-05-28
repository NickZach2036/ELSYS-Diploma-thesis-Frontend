package com.example.elsysdiplomathesisfrontend.data.repository

import com.example.elsysdiplomathesisfrontend.data.model.Comment
import com.example.elsysdiplomathesisfrontend.data.service.CommentService
import com.example.elsysdiplomathesisfrontend.domain.repository.CommentRepository

class CommentRepositoryImpl(private val commentService: CommentService) : CommentRepository {
    override suspend fun getCommentsForLandmark(landmarkId: Int): Result<List<Comment>> {
        return try {
            val response = commentService.getCommentsForLandmark(landmarkId)
            Result.success(response.data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
