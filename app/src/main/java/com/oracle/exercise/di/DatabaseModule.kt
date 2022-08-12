package com.oracle.exercise.di

import android.content.Context
import androidx.room.Room
import com.oracle.exercise.data.local.AppDatabase
import com.oracle.exercise.data.local.DatabaseConstants.FLIGHT_DATABASE_NAME
import com.oracle.exercise.data.local.FlightDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            FLIGHT_DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideFlightDao(appDatabase: AppDatabase): FlightDao {
        return appDatabase.flightDao()
    }
}
