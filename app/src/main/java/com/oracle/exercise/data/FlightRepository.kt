package com.oracle.exercise.data

import com.oracle.exercise.data.local.FlightDao
import com.oracle.exercise.data.remote.FlightRemoteDataSource
import com.oracle.exercise.model.Flight
import com.oracle.exercise.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository which fetches data from Remote or Local data sources
 */
class FlightRepository @Inject constructor(
    private val flightRemoteDataSource: FlightRemoteDataSource,
    private val flightDao: FlightDao
) {
    suspend fun fetchFlights(): Flow<Result<List<Flight>>?> {
        return flow {
            emit(Result.loading())
            val result = flightRemoteDataSource.getFlights()
            // Cache to database if response is successful
            if (result.status == Result.Status.SUCCESS) {
                result.data?.let { it ->
                    flightDao.deleteAllFlights()
                    flightDao.insertFlights(it)
                }
                emit(result)
            } else {
                emit(fetchCachedFlights())
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchCachedFlights(): Result<List<Flight>> {
        return flightDao.getAllFlights()?.let {
            if (it.isNotEmpty()) {
                Result.success(it)
            } else {
                Result.error("No cached data", null)
            }
        } ?: Result.error("No cached data", null)
    }
}
