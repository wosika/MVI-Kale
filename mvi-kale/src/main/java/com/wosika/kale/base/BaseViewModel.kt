package com.wosika.kale.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wosika.kale.intent.IIntent
import com.wosika.kale.viewmodel.IViewModel
import com.wosika.kale.viewstate.IViewState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


abstract class BaseViewModel<VS : IViewState, I : IIntent> : ViewModel(), IViewModel<VS, I> {
    override fun viewStateObservable(): StateFlow<VS> = viewStateFlow

    protected abstract val initState: VS


    /**
     * 增加viewState
     * @param viewState VS 需要发送给view层的viewState
     */
    protected fun addViewState(viewState: VS) {
        viewStateFlow.value = viewState
    }


    //允许修改
    protected open val viewStateFlow: MutableStateFlow<VS> by lazy {
        MutableStateFlow(initState)
    }

    override fun intent(intent: I) {
        viewModelScope.launch(Dispatchers.Default) {
            //子线程处理
            processIntent(intent)
        }
    }

    protected abstract suspend fun processIntent(intent: I)

}

