package com.example.mertkesgin.weatherapp.ui.citiesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mertkesgin.weatherapp.R
import com.example.mertkesgin.weatherapp.core.ImageHelper
import com.example.mertkesgin.weatherapp.domain.weather.model.CurrentWeather
import kotlinx.android.synthetic.main.item_city.view.*

class CityListAdapter(val viewModel: ListViewModel) :
    RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    val differCallback = object : DiffUtil.ItemCallback<CurrentWeather>() {
        override fun areItemsTheSame(oldItem: CurrentWeather, newItem: CurrentWeather): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CurrentWeather, newItem: CurrentWeather): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_city,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val weather = differ.currentList?.get(position)
        holder.itemView.apply {
            tvCityName.text = weather?.name
            tvItemTemp.text = weather?.temperature
            weatherDescriptionTV.text = weather?.weatherDesc
            deleteBtn.setOnClickListener {
                viewModel.removeLocation(weather.name)
                val cloneList = differ.currentList.toMutableList()
                cloneList.removeAt(position)
                differ.submitList(cloneList)
            }
            weather?.let { ImageHelper().loadUrl(it?.weatherIcon, imgItem) }
            setOnClickListener {
                it.findNavController()
                    .navigate(ListCitiesWeatherFragmentDirections.navigateToWeatherDetail(weather))
            }
        }
    }
}
