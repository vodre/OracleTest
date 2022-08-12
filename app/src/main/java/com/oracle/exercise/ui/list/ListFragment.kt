package com.oracle.exercise.ui.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.oracle.exercise.R
import com.oracle.exercise.databinding.FragmentListBinding
import com.oracle.exercise.model.Flight
import com.oracle.exercise.ui.list.model.Failure
import com.oracle.exercise.ui.list.model.Idle
import com.oracle.exercise.ui.list.model.Loading
import com.oracle.exercise.ui.list.model.Success
import com.oracle.exercise.ui.list.model.UiModel
import com.squareup.cycler.Recycler
import com.squareup.cycler.toDataSource
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {
    private val viewModel: ListViewModel by viewModels()
    private val binding by viewBinding(FragmentListBinding::bind)
    private lateinit var recycler: Recycler<Flight>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        viewModel.viewStateFlow
            .flowWithLifecycle(lifecycle)
            .onEach { render(it) }
            .launchIn(lifecycleScope)
    }

    private fun render(uiModel: UiModel) {
        with(uiModel) {
            when (uiStatus) {
                Idle -> Unit
                Loading -> {
                    binding.animationView.playAnimation()
                    binding.animationView.isVisible = true
                }
                Success -> {
                    binding.animationView.isVisible = false
                    uiModel.flights.toDataSource().let {
                        recycler.update {
                            data = it
                        }
                    }
                }
                is Failure -> {
                    binding.animationView.isVisible = false
                    Snackbar
                        .make(requireView(), "Error", Snackbar.LENGTH_INDEFINITE)
                        .setAction("RETRY") {
                            viewModel.fetchFlights()
                        }.show()
                }

            }
        }
    }

    private fun setupUI() {
        recycler = Recycler.adopt(binding.recycler) {
            row {
                create { context ->
                    view = ListItemView(context)
                    bind { flight ->
                        view.render(flight)
                        view.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putParcelable("flight", flight)
                            findNavController()
                                .navigate(R.id.action_ListFragment_to_DetailFragment, bundle)
                        }
                    }
                }
            }
        }
    }
}
