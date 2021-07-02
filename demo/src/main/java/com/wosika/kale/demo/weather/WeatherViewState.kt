package com.wosika.kale.demo.weather

import com.wosika.kale.viewstate.IViewState

data class WeatherViewState(val isRefresh: Boolean = false, val data: String? = null, val error: Exception? =null) : IViewState