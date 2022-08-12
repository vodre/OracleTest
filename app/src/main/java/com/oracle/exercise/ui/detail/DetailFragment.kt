package com.oracle.exercise.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import coil.load
import com.oracle.exercise.R
import com.oracle.exercise.databinding.FragmentDetailBinding
import com.oracle.exercise.model.Flight
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Flight>("flight")?.let {
            setupUI(it)
        }
    }

    private fun setupUI(flight: Flight) {
        binding.missionName.text = flight.missionName
        binding.description.text = flight.details
        binding.rocketName.text = flight.rocket?.rocketName
        binding.image.load(flight.links?.missionPatch)

        flight.links?.videoLink?.let { videoUrl ->
            binding.animationView.isVisible = true
            binding.animationView.playAnimation()
            binding.animationView.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(videoUrl)
                    )
                )
            }
        }

        flight.links?.wikipedia.let { wikipediaUrl ->
            binding.readMore.isVisible = true
            binding.readMore.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(wikipediaUrl)
                startActivity(i)
            }
        }

        val status = if (flight.launchSuccess) R.string.status_success else R.string.status_failure

        binding.status.text =
            (HtmlCompat.fromHtml(getString(status), HtmlCompat.FROM_HTML_MODE_LEGACY))

        binding.toolbar.title = flight.rocket?.rocketName ?: "SpaceX"
    }
}
