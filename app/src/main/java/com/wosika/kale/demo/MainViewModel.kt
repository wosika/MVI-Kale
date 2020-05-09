package com.wosika.kale.demo

import com.wosika.kale.base.BaseViewModel


class MainViewModel : BaseViewModel<MainViewState, MainIntent>() {
    override fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.InitIntent -> {
                viewStateLiveData.postValue(MainViewState(""))
            }
        }
    }
}