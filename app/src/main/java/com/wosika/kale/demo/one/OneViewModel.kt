package com.wosika.kale.demo.one


import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet


import com.wosika.kale.base.BaseViewModel
import com.wosika.kale.demo.entity.BaseResult
import com.wosika.kale.demo.entity.Weather


import timber.log.Timber


class OneViewModel : BaseViewModel<OneViewState, OneIntent>() {


    override fun processIntent(intent: OneIntent) {
        when (intent) {
            is OneIntent.InitIntent -> {
                loadWeatherData()

            }
        }
    }


    private fun loadWeatherData() {
        //发送一个刷新的状态
        viewStateLiveData.postValue(OneViewState(isRefresh = true))
        //网络请求天气数据
        val (_, _, result) =
            "/simpleWeather/query".httpGet(
                listOf(
                    "city" to "三亚",
                    "key" to "6880a0c6e99ba78cbbf7207fd35528b3"
                )
            ).responseObject<BaseResult<Weather>>()

        result.fold({ data ->
            Timber.d(data.toString())
            //成功的返回数据
            Timber.d("请求成功")
            viewStateLiveData.postValue(OneViewState(data = data.result.toString()))
        }, { error ->
            //失败
            Timber.e(error)
            viewStateLiveData.postValue(OneViewState(error = error))
        })
    }

}

