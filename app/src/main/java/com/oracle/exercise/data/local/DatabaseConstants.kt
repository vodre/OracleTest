package com.oracle.exercise.data.local

/**
 * This object is used to provide database-related constants to the application
 */
object DatabaseConstants {
    const val FLIGHT_DATABASE_NAME = "flights.db"
    const val FLIGHT_DATABASE_VERSION = 1
    const val EXPORT_SCHEMA = false
    const val SELECT_ALL_FLIGHTS_QUERY = "SELECT * FROM Flight"
    const val DELETE_ALL_FLIGHTS_QUERY = "DELETE FROM Flight"
}
