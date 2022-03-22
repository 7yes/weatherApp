package com.example.myweatherappcat22.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myweatherappcat22.R
import com.example.myweatherappcat22.Utills.convToFah
import com.example.myweatherappcat22.adapter.WeatherAdapter
import com.example.myweatherappcat22.databinding.FragmentForecastBinding
import com.example.myweatherappcat22.model.Forecast
import com.example.myweatherappcat22.viewmodel.ResultState

private const val KEY = "KEY"

class ForecastFragment : BaseFragment() {
    private var city: String? = "Atlanta"

    private val binding by lazy {
        FragmentForecastBinding.inflate(layoutInflater)
    }

    private val weatherAdapter by lazy {
        WeatherAdapter(){  forecast: Forecast ->
            val bundle = Bundle()
            bundle.putString("CITY",city)
            bundle.putString("TEMP", convToFah(forecast.main.temp))
            bundle.putString("DESC", forecast.weather[0].description)

            findNavController().navigate(R.id.action_ForecastFragment_to_DetailsFragment, bundle)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getString(KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.forecastRV.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter = weatherAdapter
        }

        weatherViewModel.cityForecast.observe(viewLifecycleOwner, ::handleState)
// call to the API
        weatherViewModel.getForecast(city!!)

        return binding.root
    }

    private fun handleState(resultState: ResultState) {
        when(resultState) {
            is ResultState.LOADING -> {
                Toast.makeText(requireContext(), "LOADING...", Toast.LENGTH_LONG).show()
            }
            is ResultState.SUCCESS -> {
                weatherAdapter.setForecast(resultState.results.list)
                binding.temF.text = convToFah(resultState.results.list[0].main.temp)
                binding.ciudadCV.text = resultState.results.city.name

            }
            is ResultState.ERROR -> {
                Log.e("FORECAST", resultState.error.localizedMessage, resultState.error)
                Toast.makeText(requireContext(), resultState.error.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
}