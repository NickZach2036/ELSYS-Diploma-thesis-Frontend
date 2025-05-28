package com.example.elsysdiplomathesisfrontend.data.service

import com.example.elsysdiplomathesisfrontend.data.model.Landmark
import com.example.elsysdiplomathesisfrontend.data.model.LandmarkByUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LandmarkService {
    @GET(APIConstants.LANDMARKS)
    suspend fun getLandmarks(
        @Query("stationId") stationId: Int,
        @Query("walkingMinutes") walkingMinutes: Int = 15
    ): Response<List<Landmark>>

    @POST(APIConstants.LANDMARK_BY_USER)
    suspend fun userPostLandmark(@Body body: LandmarkByUser): Result<Unit>
}
