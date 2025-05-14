package com.example.elsysdiplomathesisfrontend.data.repository

import android.util.Log
import com.example.elsysdiplomathesisfrontend.data.model.Login
import com.example.elsysdiplomathesisfrontend.data.service.AuthService
import com.example.elsysdiplomathesisfrontend.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<Unit> {
        return try {
            val result = authService.login(Login(username, password))
            if (result.isSuccessful) {
                val token = result.body()?.data?.accessToken
                if (token != null) {
                    Log.d("AuthRepositoryImpl", "Token: $token")
                    Result.success(Unit)
                } else {
                    Result.failure(Throwable("No token in response"))
                }
            } else {
                Result.failure(Throwable("Login failed: ${result.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Result.failure(Throwable(e.message))
        }
    }

    override suspend fun register(username: String, password: String): Result<Unit> {
        return try {
            val response = authService.register(Login(username, password))
            Log.d("AuthRepositoryImpl", "Registered with token: ${response.data.accessToken}")
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Throwable(e.message))
        }
    }
}
