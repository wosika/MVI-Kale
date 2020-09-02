package com.wosika.kale.demo.app

import android.app.Application
import com.github.kittinunf.fuel.core.FoldableRequestInterceptor
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.interceptors.LogRequestInterceptor
import com.github.kittinunf.fuel.core.interceptors.LogResponseInterceptor
import com.github.kittinunf.fuel.core.interceptors.loggingResponseInterceptor
import com.wosika.kale.demo.di.pagingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
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
            addResponseInterceptor { LogResponseInterceptor(it) }
        }

        //初始化依赖注入
        startKoin {
            androidContext(this@App)
            modules(pagingModule)
        }
    }
}