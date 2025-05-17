package com.example.elsysdiplomathesisfrontend.data.repository

import com.example.elsysdiplomathesisfrontend.data.model.Landmark
import com.example.elsysdiplomathesisfrontend.data.model.LandmarkByUser
import com.example.elsysdiplomathesisfrontend.data.service.LandmarkService
import com.example.elsysdiplomathesisfrontend.domain.repository.LandmarkRepository

class LandmarkRepositoryImpl(private val landmarkService: LandmarkService) : LandmarkRepository {
    override suspend fun getStation(stationID: Int, walkingMinutes: Int): Result<List<Landmark>> {
        return try {
            val result = landmarkService.getLandmarks(stationID, walkingMinutes)
            if (result.isSuccessful) {
                val landmarks = result.body()
                if (landmarks != null) {
                    Result.success(landmarks)
                } else {
                    Result.failure(Throwable("No landmarks in response"))
                }
            } else {
                Result.failure(
                    Throwable("Getting landmarks failed: ${result.errorBody()?.string()}")
                )
            }
        } catch (e: Exception) {
            Result.failure(Throwable(e.message))
        }
    }

    override suspend fun userPostLandmark(
        name: String,
        description: String,
        location: String,
        stationId: Int
    ): Result<Unit> {
        return try {
            val result = landmarkService.userPostLandmark(
                LandmarkByUser(
                    name = name,
                    description = description,
                    location = location,
                    stationId = stationId
                )
            )
            if (result.isSuccess) {
                Result.success(Unit)
            } else {
                Result.failure(
                    Throwable(
                        "Creating landmarks failed: ${
                            result.exceptionOrNull()?.message 
                        }"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(Throwable(e.message))
        }
    }
}
