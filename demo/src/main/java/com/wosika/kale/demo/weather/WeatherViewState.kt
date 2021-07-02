package com.wosika.kale.demo.weather

import com.wosika.kale.demo.entity.Weather
import com.wosika.kale.viewstate.IViewState

data class WeatherViewState(val isRefresh: Boolean = false, val data: Weather? = null, val error: Exception? =null) : IViewState