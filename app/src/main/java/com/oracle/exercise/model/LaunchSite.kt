package com.oracle.exercise.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class LaunchSite(
    @SerializedName("site_id") val siteId: String,
    @SerializedName("site_name") val siteName: String?
) : Parcelable
