package com.oracle.exercise.ui.list.model

import com.oracle.exercise.model.Flight

internal data class UiModel(
    val uiStatus: UiStatus,
    val flights: List<Flight> = listOf()
) {
    companion object {
        val default
            get() = UiModel(
                uiStatus = Idle
            )
    }
}
