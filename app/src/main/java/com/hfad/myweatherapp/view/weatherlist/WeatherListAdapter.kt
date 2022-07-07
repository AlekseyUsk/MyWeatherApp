package com.hfad.myweatherapp.view.weatherlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.myweatherapp.databinding.FragmentWeatherRecycleItemBinding
import com.hfad.myweatherapp.details.OnItemClick
import com.hfad.myweatherapp.domain.Weather


class WeatherListAdapter(val dataList: List<Weather>,private val callback: OnItemClick) :
    RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = FragmentWeatherRecycleItemBinding.inflate(LayoutInflater.from(parent.context))
        return WeatherViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(weather: Weather) {
            val binding = FragmentWeatherRecycleItemBinding.bind(itemView)
            binding.cityName.text = weather.city.CityName
            binding.root.setOnClickListener {

                callback.onItemClick(weather) // ОШИБКА! - callback горит крассным
            }
        }
    }


}