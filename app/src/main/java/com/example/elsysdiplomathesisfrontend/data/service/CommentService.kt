package com.example.elsysdiplomathesisfrontend.data.service

import com.example.elsysdiplomathesisfrontend.data.model.ApiResponse
import com.example.elsysdiplomathesisfrontend.data.model.Comment
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentService {
    @GET("comments/landmark/{landmarkId}")
    suspend fun getCommentsForLandmark(@Path("landmarkId") landmarkId: Int): ApiResponse<List<Comment>>
}
