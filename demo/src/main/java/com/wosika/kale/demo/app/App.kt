package com.wosika.kale.demo.app

import android.app.Application
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.interceptors.LogRequestInterceptor
import com.github.kittinunf.fuel.core.interceptors.LogResponseInterceptor
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化log工具
        Timber.plant(Timber.DebugTree())

        //初始化网络请求
        FuelManager.instance.apply {
            basePath = "https://apis.juhe.cn"
            addRequestInterceptor {
                LogRequestInterceptor(it)
            }
            addResponseInterceptor {
                LogResponseInterceptor(it)
            }
        }
    }
}