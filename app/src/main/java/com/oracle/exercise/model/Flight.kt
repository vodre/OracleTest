package com.oracle.exercise.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Flight(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("flight_number") var flightNumber: Int,
    @SerializedName("mission_name") var missionName: String?,
    @SerializedName("release_date") var releaseDate: String?,
    @SerializedName("overview") var overview: String?,
    @SerializedName("poster_path") var posterPath: String?,
    @SerializedName("list_type") var listType: String?,
    @SerializedName("launch_success") val launchSuccess: Boolean,
    @SerializedName("details") val details: String?,
    @Embedded(prefix = "client_link")
    @SerializedName("links")
    val links: Links?,
    @Embedded(prefix = "client_rocket")
    @SerializedName("rocket")
    val rocket: Rocket?,
    @Embedded(prefix = "client_launch_site")
    @SerializedName("launch_site")
    val launchSite: LaunchSite?
) : Parcelable
