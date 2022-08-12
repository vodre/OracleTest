package com.oracle.exercise.ui.splash

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.oracle.exercise.R
import com.oracle.exercise.databinding.FragmentSplashBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val viewModel: SplashViewModel by viewModels()
    private val binding by viewBinding(FragmentSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.events
            .flowWithLifecycle(lifecycle)
            .onEach { onEvent(it) }
            .launchIn(lifecycleScope)
    }

    private fun onEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.PopLabel -> binding.textviewLaunches.isVisible = true
            SplashEvent.NavigateListScreen -> {
                findNavController().navigate(R.id.action_SplashFragment_to_ListFragment)
            }
        }
    }
}
