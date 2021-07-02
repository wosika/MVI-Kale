package com.wosika.kale.demo.weather

import com.wosika.kale.intent.IIntent

sealed class WeatherIntent : IIntent {
    data class LoadWeatherIntent(val isRefresh: Boolean) : WeatherIntent()
}