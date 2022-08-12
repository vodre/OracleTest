package com.oracle.exercise.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Rocket(
    @SerializedName("rocket_name") val rocketName: String?,
    @SerializedName("rocket_type") val rocketType: String?,
    @SerializedName("video_link") val videoLink: String?,
    @SerializedName("wikipedia") val wikipedia: String?
) : Parcelable
