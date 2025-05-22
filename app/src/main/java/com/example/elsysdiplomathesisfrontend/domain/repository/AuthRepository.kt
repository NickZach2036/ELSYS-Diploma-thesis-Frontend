package com.example.elsysdiplomathesisfrontend.domain.repository

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<Unit>
}
