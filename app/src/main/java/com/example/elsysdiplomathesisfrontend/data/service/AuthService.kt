package com.example.elsysdiplomathesisfrontend.data.service

import com.example.elsysdiplomathesisfrontend.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST(APIConstants.LOGIN)
    suspend fun login(@Body body: Login): Response<ApiResponse<Token>>

    @POST(APIConstants.REGISTER)
    suspend fun register(@Body body: Login): ApiResponse<Token>
}
