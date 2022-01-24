package com.example.mertkesgin.weatherapp.ui.current_weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mertkesgin.weatherapp.R
import com.example.mertkesgin.weatherapp.databinding.FragmentCurrentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment(R.layout.fragment_current_weather) {
    val args: CurrentWeatherFragmentArgs by navArgs()
    private val currentWeatherViewModel: CurrentWeatherViewModel by viewModels()

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrentWeatherBinding.inflate(layoutInflater, container, false)
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.refreshBtn.setOnClickListener {
            currentWeatherViewModel.getCurrentWeather(args.currentWeather.name)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.currentWeather = args.currentWeather
        setupCurrentWeatherObserver()
    }

    private fun setupCurrentWeatherObserver() {
        currentWeatherViewModel.uiState.observe(
            viewLifecycleOwner,
            Observer { uiState ->
                showLoading(uiState.isLoading)
                uiState.currentWeather.let {
                    binding.currentWeather = it
                }
            }
        )
    }

    private fun showLoading(loading: Boolean) {
        if (loading) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
