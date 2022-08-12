package com.oracle.exercise.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oracle.exercise.data.local.DatabaseConstants.DELETE_ALL_FLIGHTS_QUERY
import com.oracle.exercise.data.local.DatabaseConstants.SELECT_ALL_FLIGHTS_QUERY
import com.oracle.exercise.model.Flight

@Dao
interface FlightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFlights(flights: List<Flight>)

    @Query(SELECT_ALL_FLIGHTS_QUERY)
    fun getAllFlights(): List<Flight>?

    @Query(DELETE_ALL_FLIGHTS_QUERY)
    fun deleteAllFlights()
}
