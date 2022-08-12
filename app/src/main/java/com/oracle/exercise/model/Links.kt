package com.oracle.exercise.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Links(
    @SerializedName("mission_patch_small") val missionPatch: String?,
    @SerializedName("article_link") val articleLink: String?,
    @SerializedName("video_link") val videoLink: String?,
    @SerializedName("wikipedia") val wikipedia: String?
) : Parcelable
