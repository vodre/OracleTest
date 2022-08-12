package com.oracle.exercise.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.oracle.exercise.data.Converters
import com.oracle.exercise.data.local.DatabaseConstants.EXPORT_SCHEMA
import com.oracle.exercise.data.local.DatabaseConstants.FLIGHT_DATABASE_VERSION
import com.oracle.exercise.model.Flight

@Database(
    entities = [Flight::class],
    version = FLIGHT_DATABASE_VERSION,
    exportSchema = EXPORT_SCHEMA
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun flightDao(): FlightDao
}
