package com.example.myweatherappcat22.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.myweatherappcat22.R
import com.example.myweatherappcat22.databinding.FragmentSearchCityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.math.log

class SearchCityFragment : Fragment() {

    private val binding by  lazy {
        FragmentSearchCityBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.citySearch.addTextChangedListener{
            binding.cityBtn.isEnabled = binding.citySearch.text.isNotEmpty()
        }



        binding.cityBtn.setOnClickListener {
            val city = binding.citySearch.text.toString()
            Log.d("TAG", "onCreateView: $city")
            val bundle = Bundle()
            bundle.putString("KEY",city)
            findNavController().navigate(R.id.action_SearchFragment_to_ForecastFragment, bundle )
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}