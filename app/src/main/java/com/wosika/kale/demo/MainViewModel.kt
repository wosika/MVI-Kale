package com.wosika.kale.demo

import androidx.lifecycle.viewModelScope
import com.wosika.kale.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainViewModel : BaseViewModel<MainViewState, MainIntent>() {
    override fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.InitIntent -> {
                initData()
            }
        }
    }

    private fun initData() {
        //返回一个刷新状态
        viewStateLiveData.postValue(MainViewState(true, null, null))
        //加载
        viewModelScope.launch {
            delay(2000)
            val random = (1..2).random()
            if (random == 1) {
                viewStateLiveData.postValue(MainViewState(false, "result data", null))
            } else {
                viewStateLiveData.postValue(MainViewState(false, null, Exception()))
            }
        }
    }
}