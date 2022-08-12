package com.oracle.exercise.network.services

import com.oracle.exercise.model.Flight
import com.oracle.exercise.network.services.NetworkConstants.FLIGHT_DATA_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit API Service
 */
interface FlightService {
    @GET(FLIGHT_DATA_ENDPOINT)
    suspend fun getFlights(): Response<List<Flight>>
}
