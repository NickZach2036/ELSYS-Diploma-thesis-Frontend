package com.example.elsysdiplomathesisfrontend.domain.repository

import com.example.elsysdiplomathesisfrontend.data.model.Landmark

interface LandmarkRepository {
    suspend fun getStation(stationID: Int, walkingMinutes: Int = 15): Result<List<Landmark>>
    suspend fun userPostLandmark(name: String, description: String, location: String, stationId: Int): Result<Unit>
}
