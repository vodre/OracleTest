package com.oracle.exercise.data.remote

import com.oracle.exercise.model.Error
import com.oracle.exercise.model.Flight
import com.oracle.exercise.model.Result
import com.oracle.exercise.network.NoConnectivityException
import com.oracle.exercise.network.services.FlightService
import com.oracle.exercise.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * fetches data from remote source
 */
open class FlightRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun getFlights(): Result<List<Flight>> {
        val flightService = retrofit.create(FlightService::class.java)
        return getResponse(
            request = { flightService.getFlights() },
            defaultErrorMessage = "Error fetching Flights"
        )
    }

    private suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMessage: String
    ): Result<T> {
        return try {
            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            if (e is NoConnectivityException) {
                return Result.error(
                    e.localizedMessage ?: "No Internet",
                    Error(
                        503,
                        e.message
                    )
                )
            }
            return Result.error("Unknown Error", null)
        }
    }
}
