package com.wosika.kale.demo.app

import android.app.Application
import com.github.kittinunf.fuel.core.FoldableRequestInterceptor
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.interceptors.LogRequestInterceptor
import com.github.kittinunf.fuel.core.interceptors.LogResponseInterceptor
import com.github.kittinunf.fuel.core.interceptors.loggingResponseInterceptor
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())


        FuelManager.instance.apply {
            basePath = "https://apis.juhe.cn"
            addRequestInterceptor {
                LogRequestInterceptor(it)
            }
            addResponseInterceptor { LogResponseInterceptor(it) }
        }

    }
}