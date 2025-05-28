package com.example.elsysdiplomathesisfrontend.data.store

interface DataStoreManager {
    suspend fun getString(key: String): String?
    suspend fun setString(key: String, value: String?)
}