package com.oracle.exercise.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oracle.exercise.data.FlightRepository
import com.oracle.exercise.model.Result
import com.oracle.exercise.ui.list.model.Failure
import com.oracle.exercise.ui.list.model.Loading
import com.oracle.exercise.ui.list.model.Success
import com.oracle.exercise.ui.list.model.UiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: FlightRepository) : ViewModel() {

    private val _viewStateFlow = MutableStateFlow(UiModel.default)
    internal val viewStateFlow get() = _viewStateFlow.asStateFlow()

    init {
        fetchFlights()
    }

    fun fetchFlights() {
        viewModelScope.launch {
            repository.fetchFlights().collect {
                when (it?.status) {
                    Result.Status.SUCCESS -> {
                        _viewStateFlow.value = _viewStateFlow.value.copy(
                            uiStatus = Success,
                            flights = it.data?.toMutableList() ?: listOf()
                        )
                    }
                    Result.Status.LOADING -> {
                        _viewStateFlow.value = _viewStateFlow.value.copy(uiStatus = Loading)
                    }
                    Result.Status.ERROR -> {
                        _viewStateFlow.value = _viewStateFlow.value.copy(
                            uiStatus = Failure(Throwable(it.message))
                        )
                    }
                    else -> Unit
                }
            }
        }
    }
}
