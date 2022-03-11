package com.wosika.kale.demo.two


import androidx.lifecycle.viewModelScope
import com.wosika.kale.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class TwoViewModel : BaseViewModel<TwoViewState, TwoIntent>() {
    override suspend fun processIntent(intent: TwoIntent) {
        when (intent) {
            is TwoIntent.InitIntent -> {
                initData()
            }
        }
    }

    private fun initData() {
        //返回一个刷新状态
        addViewState( InitViewState(true))
        //加载
        viewModelScope.launch {
            delay(2000)
            val random = (1..2).random()
            if (random == 1) {
                addViewState(InitViewState(data = "result data"))

            } else {
                addViewState(InitViewState(error = Exception()))

            }
        }
    }

    override val initState: TwoViewState = InitViewState()
}