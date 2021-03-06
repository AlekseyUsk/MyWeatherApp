package com.hfad.myweatherapp.view.weatherlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.hfad.myweatherapp.MainActivity
import com.hfad.myweatherapp.R
import com.hfad.myweatherapp.databinding.FragmentWeatherListBinding
import com.hfad.myweatherapp.view.weatherlist.details.FragmentDetails
import com.hfad.myweatherapp.view.weatherlist.details.OnItemClick
import com.hfad.myweatherapp.domain.Weather
import com.hfad.myweatherapp.viewModel.AppState

class WeatherFragmentList : Fragment(), OnItemClick {

    companion object {
        fun newInstance(): WeatherFragmentList {
            return WeatherFragmentList()
        }
    }

    var isRussia = true

    private var _binding: FragmentWeatherListBinding? = null
    private val binding: FragmentWeatherListBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    lateinit var viewModel: WeatherListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherListBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(
            WeatherListViewModel::class.java
        )
        viewModel.getLiveData().observe(viewLifecycleOwner, object : Observer<AppState> {
            override fun onChanged(t: AppState) {
                renderData(t)
            }

        })

        isRussia.apply { viewModel.getWeatherListForRussia() }

        binding.floatingBtn.setOnClickListener {
            isRussia = !isRussia
            if (isRussia) {
                viewModel.getWeatherListForRussia()
            } else {
                viewModel.getWeatherListForWorld()
            }
            viewModel.getWeatherListForRussia()
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
            }
            AppState.Loading -> {
            }
            is AppState.SuccessOfOne -> {
                val result = appState.weatherDate
            }
            is AppState.SuccessMany -> {
                binding.FragmentRecyclerView.adapter =
                    WeatherListAdapter(appState.weatherList, this)
            }
        }
    }

    fun View.HW(string: String, duration: Int, actionString: String, block: (v: View) -> Unit) {
        Snackbar.make(this, string, duration).setAction("????????????????", block).show()
    }

    override fun onItemClick(weather: Weather) {
        binding.root.HW(
            "${weather.city.CityName} ",
            Snackbar.LENGTH_LONG,
            "????????????????"
        ) {
            (binding.root.context as MainActivity).supportFragmentManager.beginTransaction()
                .hide(this)
                .add(
                    R.id.container, FragmentDetails.newInstance(weather)
                ).addToBackStack("addToBackStack").commit()

        }
    }
}