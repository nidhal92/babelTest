package com.example.mertkesgin.weatherapp.ui.citiesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mertkesgin.weatherapp.R
import com.example.mertkesgin.weatherapp.databinding.FragmentListCitiesWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCitiesWeatherFragment :
    Fragment(R.layout.fragment_list_cities_weather),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private val viewModel: ListViewModel by viewModels()

    private var _binding: FragmentListCitiesWeatherBinding? = null
    private val binding get() = _binding!!
    private lateinit var cityListAdapter: CityListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListCitiesWeatherBinding.inflate(layoutInflater, container, false)
        binding.addBtn.setOnClickListener {
            showDialog()
        }
        binding.refreshBtn.setOnClickListener {
            viewModel.getCurrentWeather()
        }
        binding.searchView.setOnQueryTextListener(this)
        return binding.root
    }

    private fun showDialog() {
        AddLocationDialogFragment().show(parentFragmentManager, "ListCitiesWeatherFragment")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupCurrentWeatherObserver()
    }

    private fun setupRecyclerView() {
        cityListAdapter = CityListAdapter(viewModel)
        binding.listItem.apply {
            adapter = cityListAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupCurrentWeatherObserver() {
        viewModel.uiState.observe(
            viewLifecycleOwner,
            Observer { uiState ->
                showLoading(uiState.isLoading)
                uiState.currentWeatherList.let {

                    cityListAdapter.differ.submitList(it)
                }
            }
        )
        viewModel.addListUiState.observe(
            viewLifecycleOwner,
            Observer { uiState ->
                showLoading(uiState.isLoading)
                uiState.currentWeatherList.let {

                    cityListAdapter.differ.submitList(it)
                }
            }
        )
        viewModel.uiFilteredState.observe(
            viewLifecycleOwner,
            Observer { uiState ->
                showLoading(uiState.isLoading)
                uiState.currentWeatherList.let {
                    cityListAdapter.differ.submitList(it)
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

    override fun onQueryTextSubmit(name: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(name: String?): Boolean {
        name?.let { viewModel.filterList(it) }
        return false
    }
}
