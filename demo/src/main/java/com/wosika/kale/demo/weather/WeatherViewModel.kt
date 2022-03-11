package com.wosika.kale.demo.weather


import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet


import com.wosika.kale.base.BaseViewModel
import com.wosika.kale.demo.entity.BaseResult
import com.wosika.kale.demo.entity.Weather


import timber.log.Timber


class WeatherViewModel : BaseViewModel<WeatherViewState, WeatherIntent>() {

    override suspend fun processIntent(intent: WeatherIntent) {
        when (intent) {
            is WeatherIntent.LoadWeatherIntent -> {
                loadWeatherData(intent.isRefresh)
            }
        }
    }

    private fun loadWeatherData(isRefresh: Boolean = false) {
        //发送一个刷新的状态
        addViewState(WeatherViewState(isRefresh = isRefresh))
        //网络请求天气数据
        val (_, _, result) =
            "/simpleWeather/query".httpGet(listOf("city" to "雅安", "key" to "6880a0c6e99ba78cbbf7207fd35528b3")
            ).responseObject<BaseResult<Weather>>()

        result.fold({ data ->
            Timber.d(data.toString())
            //成功的返回数据
            Timber.d("请求成功")
            addViewState(WeatherViewState(data = data.result))
        }, { error ->
            //失败
            Timber.e(error)
            addViewState(WeatherViewState(error = error))
        })
    }

}

