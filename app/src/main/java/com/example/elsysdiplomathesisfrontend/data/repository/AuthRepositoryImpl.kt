package com.example.elsysdiplomathesisfrontend.data.repository

import com.example.elsysdiplomathesisfrontend.data.model.Login
import com.example.elsysdiplomathesisfrontend.data.service.AuthService
import com.example.elsysdiplomathesisfrontend.data.service.DataStoreKeys
import com.example.elsysdiplomathesisfrontend.data.store.DataStoreManager
import com.example.elsysdiplomathesisfrontend.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val dataStore: DataStoreManager
) : AuthRepository {
    override suspend fun login(username: String, password: String): Result<Unit> {
        return try {
            val result = authService.login(Login(username, password))
            if (result.isSuccessful) {
                val token = result.body()?.data?.token
                if (token != null) {
                    dataStore.setString(DataStoreKeys.ACCESS_TOKEN, token)
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

            if (response.isSuccessful) {
                val token = response.body()?.data?.token
                if (token != null) {
                    dataStore.setString(DataStoreKeys.ACCESS_TOKEN, token)
                    Result.success(Unit)
                } else {
                    Result.failure(Throwable("No token in response"))
                }
            } else {
                Result.failure(Throwable("Sign up failed: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Result.failure(Throwable(e.message))
        }
    }
}
