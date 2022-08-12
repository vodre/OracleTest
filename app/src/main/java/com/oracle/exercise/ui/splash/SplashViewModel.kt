package com.oracle.exercise.ui.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val SPLASH_TIME_OUT = 5000L
const val ICON_POP_TIME = 2000L

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private var _events = MutableSharedFlow<SplashEvent>()
    val events = _events.asSharedFlow()

    init {
        startTimers()
    }

    private fun startTimers() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModelScope.launch { _events.emit(SplashEvent.PopLabel) }
        }, ICON_POP_TIME)

        Handler(Looper.getMainLooper()).postDelayed({
            viewModelScope.launch { _events.emit(SplashEvent.NavigateListScreen) }
        }, SPLASH_TIME_OUT)
    }
}

sealed interface SplashEvent {
    object NavigateListScreen : SplashEvent
    object PopLabel : SplashEvent
}
