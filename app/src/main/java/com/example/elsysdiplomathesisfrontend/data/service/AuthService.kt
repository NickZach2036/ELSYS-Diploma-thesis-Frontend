package com.example.elsysdiplomathesisfrontend.data.service

import com.example.elsysdiplomathesisfrontend.data.model.Login
import com.example.elsysdiplomathesisfrontend.data.model.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST(APIConstants.AUTH)
    suspend fun login(@Body body: Login): Response<Token>
}
