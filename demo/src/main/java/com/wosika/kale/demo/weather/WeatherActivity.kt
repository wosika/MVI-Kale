package com.wosika.kale.demo.weather

import android.annotation.SuppressLint
import android.os.Bundle
import com.github.matteobattilana.weather.PrecipType
import com.google.android.material.snackbar.Snackbar
import com.wosika.kale.base.BaseActivity
import com.wosika.kale.demo.databinding.ActivityWeatherBinding

import com.wosika.kale.demo.entity.Weather
import com.wosika.kale.viewmodel.createViewModel

// 天气效果的view:  https://github.com/MatteoBattilana/WeatherView .感谢开源
class WeatherActivity : BaseActivity<WeatherViewState, WeatherIntent>() {

    val bind by lazy { ActivityWeatherBinding.inflate(layoutInflater) }


    override val viewModel: WeatherViewModel by lazy { createViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(bind.root)
        binds()
        //对viewmodel发送初始化意图
        viewModel.intent(WeatherIntent.LoadWeatherIntent(true))
    }

    private fun binds() {
        bind.srlRefresh.setOnRefreshListener {
            viewModel.intent(WeatherIntent.LoadWeatherIntent(false))
        }
    }


    @SuppressLint("SetTextI18n")
    override fun render(viewState: WeatherViewState) {
        bind.srlRefresh.isRefreshing = viewState.isRefresh
        if (viewState.data != null) {
            renderWeatherView(viewState.data)
            bind.tvWeather.text = viewState.data.realtime.info
            bind.tvTemperature.text = "${viewState.data.realtime.temperature}℃"
            bind.tvCity.text = viewState.data.city
        }
        if (viewState.error != null) {
            Snackbar.make(
                bind.srlRefresh,
                viewState.error.message ?: "数据加载错误",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    //渲染天气
    private fun renderWeatherView(data: Weather) {
        when (data.realtime.wid) {
            //晴天
            "00", "01", "02" -> {
                bind.weatherView.setWeatherData(PrecipType.CLEAR);
            }
            //雨天
            "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "21", "22", "23", "24", "25" -> {
                bind.weatherView.setWeatherData(PrecipType.RAIN)
            }
            //雪
            "13", "14", "15", "16", "17", "26", "27", "28" -> {
                bind.weatherView.setWeatherData(PrecipType.SNOW)
            }
            //雾
            "18", "53" -> {
                bind.weatherView.setWeatherData(PrecipType.CUSTOM)
            }
            //沙
            "20", "29", "30", "31" -> {
            }
            else -> {
                bind.weatherView.setWeatherData(PrecipType.CLEAR)
            }
        }
    }


}

