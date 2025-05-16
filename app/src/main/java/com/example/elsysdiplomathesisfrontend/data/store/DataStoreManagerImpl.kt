package com.example.elsysdiplomathesisfrontend.data.store

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
class DataStoreManagerImpl(private val dataStore: DataStore<HashMap<String, String>>) : DataStoreManager {
    override suspend fun getString(key: String): String? {
        return dataStore.data.map { it[key] }.first()
    }

    override suspend fun setString(key: String, value: String?) {
        dataStore.updateData {
            HashMap(it).apply { put(key, value) }
        }
    }
}
