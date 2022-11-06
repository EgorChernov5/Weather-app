package com.example.weather.view

import com.example.weather.R
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.view.adapter.WeatherAdapter
import com.example.weather.viewmodel.WeatherViewModel
import com.example.weather.viewmodel.factory

import com.example.weather.CITY


class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private lateinit var adapter: WeatherAdapter

    private val viewModel: WeatherViewModel by viewModels { factory() }

    private var city = CITY
    private lateinit var builder: AlertDialog.Builder
    private lateinit var toolbar: Toolbar
    private lateinit var alertEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        toolbar = binding.toolbar
        toolbar.title = "Weather"
        toolbar.inflateMenu(R.menu.menu_main)

        toolbar.setOnMenuItemClickListener {
            alertEditText = EditText(requireContext())

            builder = AlertDialog.Builder(requireContext())
            builder
                .setTitle("Write your city:")
                .setMessage("Example: London,gb")
                .setView(alertEditText)
                .setNegativeButton("Cancel") { dialogInterface, _ ->
                    dialogInterface.cancel()
                }
                .setPositiveButton("Save") { _, _ ->
                    city = alertEditText.text.toString()
                    viewModel.loadWeather(city)
                }
                .show()
            true
        }

        viewModel.loadWeather(city)
        adapter = WeatherAdapter()

        viewModel.weather.observe(viewLifecycleOwner) {
            adapter.weatherList = it.list
            binding.city.text = it.city.name
            binding.temp.text = it.list[0].main.temp.toString().plus(" `C")
            binding.desc.text = it.list[0].weather[0].description
        }

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        binding.finderButton.setOnClickListener {
            val city = binding.finder.text.toString()
            viewModel.loadWeather(city)
            if (viewModel.resp.value!!.code != 200) {
                Toast.makeText(
                    requireContext(),
                    viewModel.resp.value!!.message,
                    Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}