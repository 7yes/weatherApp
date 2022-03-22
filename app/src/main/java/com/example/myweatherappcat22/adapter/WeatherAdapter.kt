package com.example.myweatherappcat22.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherappcat22.Utills.convToFah
import com.example.myweatherappcat22.databinding.ForcastItemBinding
import com.example.myweatherappcat22.model.Forecast
import com.example.myweatherappcat22.model.Main

class WeatherAdapter(
  private var forecastList: List<Forecast> = listOf(),
  private val onForescastClick: (Forecast) -> Unit
) : RecyclerView.Adapter<ForecastViewHolder>() {

    fun setForecast(newForecast: List<Forecast>) {
        forecastList = newForecast

        notifyItemRangeChanged(0, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = ForcastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(view, onForescastClick)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) =
        holder.bind(forecastList[position])

    override fun getItemCount(): Int = forecastList.size
}

class ForecastViewHolder(
    private val binding: ForcastItemBinding,
    private val onForescastClick: (Forecast) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(forecast: Forecast) {

        binding.tempCV.text = convToFah(forecast.main.temp)
        binding.horaCV.text = "Feels Like ${convToFah(forecast.main.feelsLike)}"
        binding.maxminCV.text = "${convToFah(forecast.main.tempMax)}/${convToFah(forecast.main.tempMin)}"
        binding.root.setOnClickListener {onForescastClick(forecast)
        }

        //binding.mRating.setProgress(2)
    }

}