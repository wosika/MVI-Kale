package com.wosika.kale.demo.one

import androidx.lifecycle.viewModelScope
import com.wosika.kale.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class OneViewModel : BaseViewModel<OneViewState, OneIntent>() {
    override fun processIntent(intent: OneIntent) {
        when (intent) {
            is OneIntent.InitIntent -> {
                initData()
            }
        }
    }

    private fun initData() {
        //返回一个刷新状态
        viewStateLiveData.postValue(
            OneViewState(isRefresh = true)
        )
        //加载
        viewModelScope.launch {
            delay(2000)
            val random = (1..2).random()
            if (random == 1) {
                viewStateLiveData.postValue(
                    OneViewState(data = "result data")
                )
            } else {
                viewStateLiveData.postValue(
                    OneViewState(
                        error = Exception()
                    )
                )
            }
        }
    }
}