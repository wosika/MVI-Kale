package com.wosika.kale.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wosika.kale.intent.IIntent
import com.wosika.kale.viewmodel.IViewModel
import com.wosika.kale.viewstate.IViewState
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow


abstract class BaseViewModel<VS : IViewState, I : IIntent> : ViewModel(), IViewModel<VS, I> {

    override fun viewStateObservable(): Flow<VS> = viewStateFlow.consumeAsFlow()

    /**
     * 增加viewState
     * @param viewState VS 需要发送给view层的viewState
     */
    protected fun addViewState(viewState: VS) {
        viewModelScope.launch {
            viewStateFlow.send(viewState)
        }
    }

    override fun onCleared() {
        viewStateFlow.cancel()
        super.onCleared()
    }

    //允许修改
    protected open val viewStateFlow: Channel<VS> by lazy {
        Channel(Channel.CONFLATED)
    }

    override fun intent(intent: I) {
        viewModelScope.launch(Dispatchers.Default) {
            //子线程处理
            processIntent(intent)
        }
    }

    protected abstract suspend fun processIntent(intent: I)

}

