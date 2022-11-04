package com.example.weather.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.WeatherAdapter
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.models.WeatherList
import com.example.weather.models.WeatherService


//private const val ARG_CITY = "ARG_CITY"
//private const val ARG_TEMP = "ARG_TEMP"
//private const val ARG_DESC = "ARG_DESC"


class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private lateinit var adapter: WeatherAdapter

    private val viewModel: WeatherViewModel by viewModels { factory() }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        arguments?.let {
////            param1 = it.getString(ARG_PARAM1)
////            param2 = it.getString(ARG_PARAM2)
////        }
//        viewModel.loadWeather()
//    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        adapter = WeatherAdapter()

        viewModel.weather.observe(viewLifecycleOwner, Observer {
            adapter.weatherList = it.weatherList
            binding.city.text = it.city.name
            binding.temp.text = it.weatherList[0].mainInfo.temp.toString() + " C"
            binding.desc.text = it.weatherList[0].weatherDesc[0].description
        })

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        return binding.root
    }

//    companion object {
//        @JvmStatic
//        fun newInstance(city: String, temp: String, desc: String) =
//            WeatherFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_CITY, city)
//                    putString(ARG_TEMP, temp)
//                    putString(ARG_DESC, desc)
//                }
//            }
//    }
}