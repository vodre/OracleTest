package com.oracle.exercise.ui.list

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.view.ContextThemeWrapper
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import com.oracle.exercise.R
import com.oracle.exercise.databinding.ViewItemBinding
import com.oracle.exercise.model.Flight
import com.oracle.exercise.util.extensions.layoutInflater

class ListItemView(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(
    ContextThemeWrapper(context, R.style.Theme_OracleTest),
    attributeSet
) {
    private val binding = ViewItemBinding.inflate(layoutInflater, this, true)

    fun render(flight: Flight) {
        val flightNumber = "Flight Number: ${flight.flightNumber}"
        binding.image.load(flight.links?.missionPatch)
        binding.rocketType.text = flight.rocket?.rocketType
        binding.rocketName.text = flight.rocket?.rocketName
        binding.missionName.text = flight.missionName
        binding.launchSite.text = flight.launchSite?.siteName
        binding.flightNumber.text = flightNumber
    }
}
