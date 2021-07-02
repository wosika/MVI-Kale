package com.wosika.kale.base


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wosika.kale.intent.IIntent
import com.wosika.kale.viewmodel.IViewModel
import com.wosika.kale.viewstate.IViewState
import kotlinx.coroutines.*


abstract class BaseViewModel<VS : IViewState, I : IIntent> : ViewModel(), IViewModel<VS, I> {
    override fun viewStateObservable(): LiveData<VS> = viewStateLiveData


    /**
     * "使用postViewState在频繁刷新数据时有可能丢失,如果为了保证数据能够传送过去请使用addViewState"
     * @param viewState VS 需要发送给view层的viewState
     */
    protected fun postViewState(viewState: VS) {
        viewStateLiveData.postValue(viewState)
    }

    /**
     * liveData中的setValue只能在主线程中使用,所以使用协程切换主线程后再调用
     * @param viewState VS 需要发送给view层的viewState
     */
    protected fun addViewState(viewState: VS) {
        viewModelScope.launch(Dispatchers.Main) {
            viewStateLiveData.value = viewState
        }
    }


    //允许修改
    protected open val viewStateLiveData: MutableLiveData<VS> by lazy {
        MutableLiveData<VS>()
    }

    override fun intent(intent: I) {
        viewModelScope.launch(Dispatchers.Default) {
            //子线程处理
            processIntent(intent)
        }
    }

    protected abstract fun processIntent(intent: I)

}

