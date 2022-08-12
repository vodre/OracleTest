package com.oracle.exercise.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.oracle.exercise.model.Flight
import com.oracle.exercise.model.Links

/**
 * Class is used by Room database to convert unknown data types
 * to JSON (String format) and then back to their original data types
 * when retrieved from the database.
 */
class Converters {
    @TypeConverter
    fun flightListToJson(flights: List<Flight>): String {
        return Gson().toJson(flights)
    }

    @TypeConverter
    fun flightListFromJson(value: String): List<Flight> {
        return Gson().fromJson(value, Array<Flight>::class.java).toList()
    }
}
