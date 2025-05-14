package com.example.elsysdiplomathesisfrontend.data.service

import com.example.elsysdiplomathesisfrontend.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST(APIConstants.LOGIN)
    suspend fun login(@Body body: Login): Response<ApiResponse<Token>>

    @POST(APIConstants.REGISTER)
    suspend fun register(@Body body: Login): ApiResponse<Token>

    @GET(APIConstants.LANDMARKS)
    suspend fun getLandmarks(
        @Query("stationId") stationId: Int,
        @Query("walkingMinutes") walkingMinutes: Int = 15
    ): List<Landmark>
}
