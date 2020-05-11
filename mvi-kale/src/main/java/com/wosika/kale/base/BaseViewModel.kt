package com.wosika.kale.base


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wosika.kale.intent.IIntent
import com.wosika.kale.viewmodel.IViewModel
import com.wosika.kale.viewstate.IViewState
import kotlinx.coroutines.*

abstract class BaseViewModel<VS:IViewState,I:IIntent> : ViewModel(), IViewModel<I,VS> {

    override fun viewStateObservable(): LiveData<VS> = viewStateLiveData

    protected val viewStateLiveData: MutableLiveData<VS> by lazy {
        MutableLiveData<VS>()
    }

    override fun intent(intent: I) {
        viewModelScope.launch(Dispatchers.Default) {
            //子线程处理
            processIntent(intent)
        }
    }

    protected abstract fun processIntent(intent: I)

    override fun onCleared() {
        super.onCleared()
    }

}