package com.oracle.exercise.ui.list.model

sealed interface UiStatus
object Idle : UiStatus
object Loading : UiStatus
object Success : UiStatus
class Failure : UiStatus {
    val error: Throwable?

    constructor(error: Throwable? = null) {
        this.error = error
    }

    constructor(block: () -> Throwable) {
        error = block()
    }
}
