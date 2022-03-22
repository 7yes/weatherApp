package com.example.myweatherappcat22.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myweatherappcat22.R
import com.example.myweatherappcat22.databinding.FragmentFforecastDetailsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val CITY = "CITY"
private const val TEMP = "TEMP"
private const val DESC = "DESC"


class ForecastDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var city: String? = null
    private var temp: String? = null
    private var desc: String? = null
    private val binding by lazy {
        FragmentFforecastDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getString(CITY)
            temp = it.getString(TEMP)
            desc = it.getString(DESC)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Asign values in my layout
        binding.city.text = city
        binding.tempCV.text = temp
        binding.description.text = desc
        return binding.root
    }
}