package com.wosika.kale.demo.weather

import com.wosika.kale.demo.entity.Weather
import com.wosika.kale.viewstate.IViewState




sealed class WeatherViewState : IViewState
data class DataViewState(val isRefresh: Boolean = false, val data: Weather? = null, val error: Exception? =null) : WeatherViewState()

class InitViewState:WeatherViewState()