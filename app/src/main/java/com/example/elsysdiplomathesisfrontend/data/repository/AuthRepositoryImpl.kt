package com.example.elsysdiplomathesisfrontend.data.repository

import android.util.Log
import com.example.elsysdiplomathesisfrontend.data.model.Login
import com.example.elsysdiplomathesisfrontend.data.service.AuthService
import com.example.elsysdiplomathesisfrontend.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {
    override suspend fun login(username: String, password: String): Result<Unit> {
        val result = authService.login(Login(username, password))

        try {
            return if (result.isSuccessful) {
                result.body()?.let { token ->
                    Log.d("TAG", "token: $token")
                }
                Result.success(Unit)
            } else {
                val message = result.errorBody()?.toString()
                Log.d("TAG", "token: $message")
                Result.failure(Throwable(message))
            }
        } catch(exception: Exception) {
            Log.d("TAG", "token: ${exception.message}")
            return Result.failure(Throwable(exception.message))
        }
    }
}