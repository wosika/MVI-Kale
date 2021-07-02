package com.wosika.kale.demo.weather

import com.wosika.kale.intent.IIntent

sealed class WeatherIntent : IIntent {
    object InitIntent : WeatherIntent()

}