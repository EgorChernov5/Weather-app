package com.example.weather.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.view.adapter.WeatherAdapter
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.viewmodel.WeatherViewModel
import com.example.weather.viewmodel.factory

import com.example.weather.CITY


class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private lateinit var adapter: WeatherAdapter

    private val viewModel: WeatherViewModel by viewModels { factory() }

    private var city = CITY

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        binding.finderButton.setOnClickListener {
            city = binding.finder.text.toString()
            viewModel.loadWeather(city)
            if (viewModel.resp.value!!.code != 200) {
                Toast.makeText(requireContext(), viewModel.resp.value!!.message, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loadWeather(city)
        adapter = WeatherAdapter()

        viewModel.weather.observe(viewLifecycleOwner, Observer {
            adapter.weatherList = it.list
            binding.city.text = it.city.name
            binding.temp.text = it.list[0].main.temp.toString() + " `C"
            binding.desc.text = it.list[0].weather[0].description
        })

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}